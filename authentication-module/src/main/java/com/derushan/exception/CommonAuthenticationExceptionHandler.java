/**
 * 
 */
package com.derushan.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Derushan Sep 21, 2020
 */
public class CommonAuthenticationExceptionHandler extends AuthenticationException{
	private static final long serialVersionUID = 1L;
	
	public CommonAuthenticationExceptionHandler(String msg) {
		super(msg);
	}
}
