/**
 * 
 */
package com.derushan.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;

/**
 * @author Derushan
 * Sep 21, 2020
 */
@Entity
@Table(name = "roles")
@ApiModel(description="All details about the roles. ")
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", unique = true, nullable = false, length = 50)
	@NotNull
	@NotEmpty(message = "Name cannot be empty")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	
	@Column(columnDefinition = "LONGTEXT")
	private String description;

	/**
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public Role(Integer id,
			@NotEmpty(message = "Name cannot be empty") @Size(min = 2, message = "Name should have atleast 2 characters") String name,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * @param name
	 * @param description
	 */
	public Role(
			@NotEmpty(message = "Name cannot be empty") @Size(min = 2, message = "Name should have atleast 2 characters") String name,
			String description) {
		super();
		this.name = name;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
