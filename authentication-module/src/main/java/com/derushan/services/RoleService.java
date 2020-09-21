/**
 * 
 */
package com.derushan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.derushan.entities.Role;
import com.derushan.repositories.RoleRepository;

/**
 * @author Derushan Sep 21, 2020
 */
@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;

	public Role getRoleByName(String name) {
		return roleRepository.findByName(name);
	}

	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	public Page<Role> getRoles(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

	public Role getRoleById(Integer id) {
		return roleRepository.findById(id).orElse(null);
	}

	public Role updateRole(Integer id, Role role) {
		Role existingRole = roleRepository.findById(id).orElse(null);
		if (existingRole == null) {
			return null;
		}
		existingRole.setName(role.getName());
		existingRole.setDescription(role.getDescription());
		return roleRepository.save(existingRole);

	}

	public String deleteRole(Integer id) {
		Role existingRole = roleRepository.findById(id).orElse(null);
		if (existingRole == null) {
			return null;
		}
		roleRepository.deleteById(id);
		return "Role deleted successfully!!!";
	}
}
