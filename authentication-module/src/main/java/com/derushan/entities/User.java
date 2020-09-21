/**
 * 
 */
package com.derushan.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModel;

/**
 * @author Derushan Sep 21, 2020
 */
@Entity
@Table(name = "users")
@ApiModel(description = "All details about the users. ")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Integer id;

	@Column(nullable = false, length = 100)
	@NotEmpty(message = "Name cannot be empty")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;

	@Column(unique = true, nullable = false, length = 255)
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Invalid email")
	private String email;

	@Column(nullable = false, columnDefinition = "LONGTEXT")
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 8, message = "Password should have atleast 8 characters")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Column(columnDefinition = "LONGTEXT")
	@JsonIgnore
	private String token;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<Role> roles = new HashSet<>();

	@Column(columnDefinition = "LONGTEXT")
	private String description;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param token
	 * @param isActive
	 * @param roles
	 * @param description
	 */
	public User(Integer id,
			@NotEmpty(message = "Name cannot be empty") @Size(min = 2, message = "Name should have atleast 2 characters") String name,
			@NotEmpty(message = "Email cannot be empty") @Email(message = "Invalid email") String email,
			@NotEmpty(message = "Password cannot be empty") @Size(min = 8, message = "Password should have atleast 8 characters") String password,
			String token, boolean isActive, Set<Role> roles, String description) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.token = token;
		this.isActive = isActive;
		this.roles = roles;
		this.description = description;
	}

	/**
	 * @param name
	 * @param email
	 * @param password
	 * @param token
	 * @param isActive
	 * @param roles
	 * @param description
	 */
	public User(
			@NotEmpty(message = "Name cannot be empty") @Size(min = 2, message = "Name should have atleast 2 characters") String name,
			@NotEmpty(message = "Email cannot be empty") @Email(message = "Invalid email") String email,
			@NotEmpty(message = "Password cannot be empty") @Size(min = 8, message = "Password should have atleast 8 characters") String password,
			String token, boolean isActive, Set<Role> roles, String description) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.token = token;
		this.isActive = isActive;
		this.roles = roles;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
