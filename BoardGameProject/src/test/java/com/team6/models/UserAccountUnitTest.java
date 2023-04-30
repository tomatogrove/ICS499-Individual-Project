package com.team6.models;

import com.team6.model.util.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
