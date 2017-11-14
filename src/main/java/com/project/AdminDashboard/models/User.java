package com.project.AdminDashboard.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User{
	@Id
	@GeneratedValue
	private Long id;

	@Email
	private String email;

	@Size(min=2, message="First Name should be at least 2 characters long!")
	private String firstName;

	@Size(min=2, message="Last Name should be at least 2 characters long!")	
	private String lastName;

	private String password;

	@Transient
	private String passwordConfirmation;

	@Column(updatable=false)
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;

	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="users_roles",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name="role_id")
	)
	private List<Role> roles;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	// Setters and Getters go here

	public User(){}
	
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the passwordConfirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	/**
	 * @param passwordConfirmation the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
