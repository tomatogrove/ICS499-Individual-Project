package com.ics499.team4.models;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.team4.model.UserAccount;

public class UserAccountUnitTest {

	UserAccount user;
	
	@BeforeEach
	public void setup() {
		user = new UserAccount();
	}
	
	@Test
	public void testSettersAndGetters() {
		user.setUsername("testUser");
		user.setPassword("testPass");
		user.setEmail("testUser@email.com");
		
		assertEquals("testUser", user.getUsername());
		assertEquals("testPass", user.getPassword());
		assertEquals("testUser@email.com", user.getEmail());
	}
}
