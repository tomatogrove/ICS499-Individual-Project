package com.team4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserAccount {
	
	@Id
	@GeneratedValue
	private Long userID;
	
	@Column(unique = true)
	private String username;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private boolean guest;
	
	public UserAccount() {}
	
	public UserAccount(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public Long getUserID() { return userID; }
	public void setUserID(Long userID) { this.userID = userID; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public boolean isGuest() { return guest; }
	public void setGuest(boolean guest) { this.guest = guest; }
}
