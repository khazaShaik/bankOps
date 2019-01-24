package com.symbo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.symbo.domain.User;
import com.symbo.domain.security.UserRole;

public interface UserService {
	
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	
	boolean checkUserExists(String username, String email);
	boolean checkUsernameExists(String username);
	boolean checkEmailExists(String email);
	
	void save(User user);
	User createUser(User user, Set<UserRole> userRoles);
	User saveUser(User user);
	
	List<User> findUserList();

    void enableUser (String username);

    void disableUser (String username);
}
