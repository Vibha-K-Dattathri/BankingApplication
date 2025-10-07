package com.ofss;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class OfssUser {
	@Id
	@Column(name="ID")
	private long id;
	private String userName;
	private String password;
	private String roles; // comma separated roles
	public OfssUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OfssUser(long id, String userName, String password, String roles) {
		super();
		
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
}

