/**
 * 
 */
package com.derushan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.derushan.entities.User;
import com.derushan.repositories.UserRepository;


/**
 * @author Derushan
 * Sep 21, 2020
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(true);
		return userRepository.save(user);
	}

	public User getOneById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	public User getOneByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Page<User> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public User updateUser(Integer id, User user) {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser == null) {
			return null;
		}
		existingUser.setName(user.getName());
		return userRepository.save(existingUser);
	}

	public User updateUserToken(Integer id, String token) {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser == null) {
			return null;
		}
		existingUser.setToken(token);
		return userRepository.save(existingUser);
	}

	public User updateUserPassword(Integer id, String password) {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser == null) {
			return null;
		}
		existingUser.setPassword(passwordEncoder.encode(password));
		return userRepository.save(existingUser);
	}

	public String deleteUser(Integer id) {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser == null) {
			return null;
		}
		userRepository.deleteById(id);
		return "User deleted successfully!!!";
	}
}
