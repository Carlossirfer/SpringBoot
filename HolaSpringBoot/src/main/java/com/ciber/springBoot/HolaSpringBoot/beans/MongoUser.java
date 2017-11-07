/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.beans;

import java.util.List;

import javax.persistence.Id;

/**
 * @author ciber
 *
 */
public class MongoUser {
	
	@Id
	private String id;
	private String username;
	private String password;
	private List<String> roles;
	/**
	 * 
	 */
	public MongoUser() {
	}
	/**
	 * @return the id
	 */
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MongoUser [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
	

}
