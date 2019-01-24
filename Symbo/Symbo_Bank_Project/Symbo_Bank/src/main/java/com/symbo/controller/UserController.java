package com.symbo.controller;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symbo.domain.User;
import com.symbo.message.request.UserProfileForm;
import com.symbo.message.response.ResponseMessage;
import com.symbo.service.UserService;

/**
 * @author khaza.shaik
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("/api/myProfile")
	private ResponseEntity<User> viewUserProfile(Principal principal) {
		User user = userService.findByUsername(principal.getName()).get();
		if(user!=null){
			return new ResponseEntity<>(user, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping("/api/createUserProfile")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserProfileForm newUser) {
		// Creating user's account
		User user = userService.findByUsername(newUser.getUsername()).get();
		if(user==null){
			user=new User();
			user.setUsername(newUser.getUsername());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			userService.save(user);
			return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is not found!"),
					HttpStatus.NOT_FOUND);
		}
			
	}

}
