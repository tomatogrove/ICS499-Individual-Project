package com.team4.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Session {
	
	@Id
	@GeneratedValue
	private Long sessionID;
	
	@Column(unique = true)
	private String sessionKey;
	
	@JsonBackReference(value="user-session")
	@OneToOne()
	private UserAccount userAccount;
	
	public Session() {}

	public Long getSessionID() {
		return sessionID;
	}

	public void setSessionID(Long sessionID) {
		this.sessionID = sessionID;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	public static String generateSessionKey() {
		return UUID.randomUUID().toString().replaceAll("_", "");
	}
}
