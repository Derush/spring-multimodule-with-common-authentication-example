/**
 * 
 */
package com.derushan.controllers.authentication;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.derushan.common.CommonResponse;
import com.derushan.common.Constants;
import com.derushan.common.Validator;
import com.derushan.config.CommonJwtTokenUtil;
import com.derushan.entities.User;
import com.derushan.entities.authentication.JwtRequest;
import com.derushan.entities.authentication.JwtResponse;
import com.derushan.exception.CommonAuthenticationExceptionHandler;
import com.derushan.services.UserService;
import com.derushan.services.authentication.CommonUserDetailsService;


/**
 * @author Derushan Sep 21, 2020
 */
@RestController
@RequestMapping(Constants.BASE_URI_AUTH)
public class CommonAuthenticationController {
	@Autowired
	private CommonJwtTokenUtil commonJwtTokenUtil;

	@Autowired
	private CommonUserDetailsService commonUserDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),
					jwtRequest.getPassword()));
		} catch (DisabledException e) {
			throw new CommonAuthenticationExceptionHandler("User Inactive");
		} catch (BadCredentialsException e) {
			throw new CommonAuthenticationExceptionHandler("Invalid Credentials");
		}

		UserDetails userDetails = commonUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
		String userEmail = userDetails.getUsername();

		User user = userService.getOneByEmail(userEmail);

		String token = commonJwtTokenUtil.generateToken(user);
		userService.updateUserToken(user.getId(), token);
		return CommonResponse.builder().status(Constants.MESSAGE_HTTP_SUCCESS).msg("login successfully!!!")
				.statusCode(Constants.CODE_HTTP_SUCCESS).data(new JwtResponse(token, user))
				.entity();

	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody User user) {
		User getAlreadyExistUser = userService.getOneByEmail(user.getEmail());

		if (getAlreadyExistUser != null) {
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED)
					.msg(Validator.uniqueValidationMessage("Email")).statusCode(Constants.CODE_HTTP_BAD_REQUEST)
					.error("Already taken").entity();
		}

		

		User saveUser = userService.saveUser(user);
		return CommonResponse.builder().status(Constants.MESSAGE_HTTP_SUCCESS).msg("Registered successfully!!!")
				.statusCode(Constants.CODE_HTTP_SUCCESS).data(saveUser)
				.entity();
	}

}
