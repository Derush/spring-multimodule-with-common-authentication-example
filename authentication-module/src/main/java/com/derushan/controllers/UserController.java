/**
 * 
 */
package com.derushan.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.derushan.common.CommonResponse;
import com.derushan.common.Constants;
import com.derushan.common.Pagination;
import com.derushan.common.Validator;
import com.derushan.entities.User;
import com.derushan.services.UserService;

/**
 * @author Derushan Sep 21, 2020
 */
@RestController
@RequestMapping(Constants.BASE_URI_COMMON)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
		User getAlreadyExistUser = userService.getOneByEmail(user.getEmail());

		if (getAlreadyExistUser != null) {
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED)
					.msg(Validator.uniqueValidationMessage("Email")).statusCode(Constants.CODE_HTTP_BAD_REQUEST)
					.error("Already taken").entity();
		}

		User saveUser = userService.saveUser(user);
		return CommonResponse.builder().status(Constants.MESSAGE_HTTP_SUCCESS).msg("User added successfully!!!")
				.statusCode(Constants.CODE_HTTP_SUCCESS).data(saveUser).entity();

	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0", required = false) Integer page) {
		try {
			Page<?> getUser = userService.getAllUsers(Pagination.paginationRequest(page));
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_SUCCESS).msg("Users get successfully!")
					.statusCode(Constants.CODE_HTTP_SUCCESS).data(Pagination.paginatedData(getUser)).entity();

		} catch (Exception e) {

			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg(Constants.MESSAGE_COMMON)
					.statusCode(Constants.CODE_HTTP_EXPECTATION_FAILED).error(e.toString()).entity();
		}
	}
}
