package com.symbo.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symbo.dao.RoleDao;
import com.symbo.domain.User;
import com.symbo.domain.security.Role;
import com.symbo.domain.security.UserRole;
import com.symbo.message.request.LoginForm;
import com.symbo.message.request.SignUpForm;
import com.symbo.message.response.JwtResponse;
import com.symbo.message.response.ResponseMessage;
import com.symbo.security.jwt.JwtProvider;
import com.symbo.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleDao roleDao;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userService.checkUsernameExists(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userService.checkEmailExists(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
	//	User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
	//			encoder.encode(signUpRequest.getPassword()));
		User user = new User();
	
			user.setUsername(signUpRequest.getUsername());
			user.setFirstName(signUpRequest.getFirstName());
			user.setLastName(signUpRequest.getLastName());
			user.setEmail(signUpRequest.getEmail());
			user.setPhone(signUpRequest.getPhone());
			user.setPassword(signUpRequest.getPassword());

			//	userService.save(user);
		
		Set<UserRole> roles = new HashSet<>();

		Role roleObj= roleDao.findByName("ROLE_"+signUpRequest.getRole().toUpperCase());
		
		UserRole userRole = new UserRole(user, roleObj);
		roles.add(userRole);

		userService.createUser(user, roles);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

}
