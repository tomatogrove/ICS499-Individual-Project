package com.team6.model.util;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class Session {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long sessionID;
	
	@Column(unique = true)
	private String sessionKey;
	
	@JsonManagedReference(value="useraccount-session")
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
		return UUID.randomUUID().toString().replaceAll("_", "").replaceAll(",", "");
	}
}
