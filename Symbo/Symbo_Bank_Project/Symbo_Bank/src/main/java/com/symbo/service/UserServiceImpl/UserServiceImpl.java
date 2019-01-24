package com.symbo.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.symbo.dao.RoleDao;
import com.symbo.dao.UserDao;
import com.symbo.domain.User;
import com.symbo.domain.security.UserRole;
import com.symbo.service.AccountService;
import com.symbo.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void save(User user) {
		userDao.save(user);
	}

	public Optional<User> findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public Optional<User> findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User createUser(User user, Set<UserRole> userRoles) {
		Optional<User> localUserOP = userDao.findByUsername(user.getUsername());
		User localuser =null;
		if (localUserOP.isPresent()) {
			localuser = localUserOP.get();
			LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
		} else {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);

			for (UserRole ur : userRoles) {
				roleDao.save(ur.getRole());
			}

			user.getUserRoles().addAll(userRoles);

			user.setPrimaryAccount(accountService.createPrimaryAccount());
			user.setSavingsAccount(accountService.createSavingsAccount());

			localuser = userDao.save(user);
		}

		return localuser;
	}

	public boolean checkUserExists(String username, String email) {
		if (checkUsernameExists(username) || checkEmailExists(email)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkUsernameExists(String username) {
		if (findByUsername(username).isPresent()) {
			return true;
		}

		return false;
	}

	public boolean checkEmailExists(String email) {
		if (findByEmail(email).isPresent()) {
			return true;
		}

		return false;
	}

	public User saveUser(User user) {
		return userDao.save(user);
	}

	public void enableUser(String username) {
		Optional<User> user = findByUsername(username);
		user.get().setEnabled(true);
		userDao.save(user.get());
	}

	public void disableUser(String username) {
		User user = findByUsername(username).get();
		user.setEnabled(false);
		System.out.println(user.isEnabled());
		userDao.save(user);
		System.out.println(username + " is disabled.");
	}

	public List<User> findUserList() {
        return userDao.findAll();
    }
	
	
}
