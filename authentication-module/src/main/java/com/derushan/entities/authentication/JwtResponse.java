/**
 * 
 */
package com.derushan.entities.authentication;

import java.io.Serializable;

import com.derushan.entities.User;

/**
 * @author Derushan Sep 21, 2020
 */
public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String token;
	private User user;

	/**
	 * @param token
	 * @param user
	 */
	public JwtResponse(String token, User user) {
		this.token = token;
		this.user = user;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

}
