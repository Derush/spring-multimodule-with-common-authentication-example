/**
 * 
 */
package com.derushan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.derushan.entities.Role;

/**
 * @author Derushan Sep 21, 2020
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
	/**
	 * @param name
	 * @return
	 */
	Role findByName(String name);
}
