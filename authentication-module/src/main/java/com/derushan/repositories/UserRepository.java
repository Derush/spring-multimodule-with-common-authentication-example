/**
 * 
 */
package com.derushan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.derushan.entities.User;

/**
 * @author Derushan Sep 21, 2020
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * @param email
	 * @return
	 */
	User findByEmail(String email);
}
