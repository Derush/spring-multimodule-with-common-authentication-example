/**
 * 
 */
package com.derushan.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.derushan.entities.User;
import com.derushan.entities.authentication.CommonUserDetails;
import com.derushan.repositories.UserRepository;

/**
 * @author Derushan
 * Sep 21, 2020
 */
@Service
public class CommonUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new CommonUserDetails(user);
	}
}
