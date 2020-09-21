/**
 * 
 */
package com.derushan.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.derushan.common.CommonResponse;
import com.derushan.common.Constants;
import com.derushan.common.Pagination;
import com.derushan.common.Validator;
import com.derushan.entities.Role;
import com.derushan.services.RoleService;

/**
 * @author Derushan Sep 21, 2020
 */
@RestController
@RequestMapping(Constants.BASE_URI_COMMON)
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/role")
	public ResponseEntity<?> addRole(@Valid @RequestBody Role role) {

		Role getAlreadyExistRole = roleService.getRoleByName(role.getName());
		if (getAlreadyExistRole != null) {
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED)
					.msg(Validator.uniqueValidationMessage("Name")).statusCode(Constants.CODE_HTTP_BAD_REQUEST)
					.error("Already taken").entity();
		}

		Role saveRole = roleService.saveRole(role);
		return CommonResponse.builder().status(Constants.MESSAGE_HTTP_SUCCESS).msg("Role added successfully!!!")
				.statusCode(Constants.CODE_HTTP_SUCCESS).data(saveRole).entity();

	}

	@PutMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@Valid @RequestBody Role role, @PathVariable Integer id) {

		Role getRole = roleService.getRoleById(id);
		if (getRole == null) {
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED)
					.msg("Role get by Id " + id + " not found").statusCode(Constants.CODE_HTTP_NOTFOUND)
					.error("Not found").entity();

		}

		Role getAlreadyExistRole = roleService.getRoleByName(role.getName());
		if (getAlreadyExistRole != null && getAlreadyExistRole.getId() != id) {
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED)
					.msg(Validator.uniqueValidationMessage("Name")).statusCode(Constants.CODE_HTTP_BAD_REQUEST)
					.error("Already taken").entity();
		}

		Role saveRole = roleService.updateRole(id, role);
		return CommonResponse.builder().status(Constants.MESSAGE_HTTP_SUCCESS).msg("Role updated successfully!!!")
				.statusCode(Constants.CODE_HTTP_SUCCESS).data(saveRole).entity();

	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/role/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable Integer id) {
		try {
			Role getRole = roleService.getRoleById(id);

			if (getRole == null) {
				return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED)
						.msg("Role get by Id " + id + " not found").statusCode(Constants.CODE_HTTP_NOTFOUND)
						.error("Not found").entity();

			}
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_SUCCESS).msg("Roles get successfully!")
					.statusCode(Constants.CODE_HTTP_SUCCESS).data(getRole).entity();

		} catch (Exception e) {
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg(Constants.MESSAGE_COMMON)
					.statusCode(Constants.CODE_HTTP_EXPECTATION_FAILED).error(e.toString()).entity();

		}

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles(@RequestParam(defaultValue = "0", required = false) Integer page) {
		try {
			Page<?> getRole = roleService.getRoles(Pagination.paginationRequest(page));
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_SUCCESS).msg("Roles get successfully!")
					.statusCode(Constants.CODE_HTTP_SUCCESS).data(Pagination.paginatedData(getRole)).entity();

		} catch (Exception e) {
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg(Constants.MESSAGE_COMMON)
					.statusCode(Constants.CODE_HTTP_EXPECTATION_FAILED).error(e.toString()).entity();

		}

	}

	@DeleteMapping("/role/{id}")
	public ResponseEntity<?> deleteRoleById(@PathVariable Integer id) {
		try {
			Role getRole = roleService.getRoleById(id);

			if (getRole == null) {
				return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED)
						.msg("Role get by Id " + id + " not found").statusCode(Constants.CODE_HTTP_NOTFOUND)
						.error("Not found").entity();
			}

			String role = roleService.deleteRole(id);
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_SUCCESS).msg(role)
					.statusCode(Constants.CODE_HTTP_SUCCESS).data(getRole).entity();

		} catch (Exception e) {
			return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg(Constants.MESSAGE_COMMON)
					.statusCode(Constants.CODE_HTTP_EXPECTATION_FAILED).error(e.toString()).entity();

		}
	}
}
