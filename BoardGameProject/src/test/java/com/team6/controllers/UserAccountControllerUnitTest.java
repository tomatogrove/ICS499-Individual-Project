package com.team6.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.team6.model.util.UserAccount;
import com.team6.services.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
// https://www.javaguides.net/2022/03/spring-boot-unit-testing-crud-rest-api-with-junit-and-mockito.html
// used as a guide


@ExtendWith(MockitoExtension.class)
public class UserAccountControllerUnitTest {


	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ObjectWriter objectWriter = objectMapper.writer();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Mock
	private UserAccountService userAccountService;
	
	@InjectMocks
	private UserAccountController userController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
	}
	
	
	@Test
	public void testCreateValidUser() throws Exception {
		UserAccount mockUserAccount = new UserAccount();
		mockUserAccount.setUsername("testUser");
		
		// whenever a post call is make for a UserAccount, return the mock user account
		when(userAccountService.createUser(any(UserAccount.class))).thenReturn(mockUserAccount);
		
		UserAccount account = new UserAccount();
		account.setUsername("testUser");
		account.setEmail("testUser@email.com");
		account.setPassword("testPass");
		
		mockMvc.perform(post("/users/add").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(account)))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testCreateInvalidUser() throws Exception {
		// test makes a post call with no body
		mockMvc.perform(post("/users/add")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetUserByID() throws Exception {
		Long userID = 1L;
		UserAccount savedUser = new UserAccount();
		savedUser.setUsername("testUser");
		savedUser.setPassword("testPass");
		
		when(userAccountService.getUserById(userID)).thenReturn(savedUser);
		
		mockMvc.perform(get("/users/{id}", userID))
		.andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(savedUser)));
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		UserAccount updatedUser = new UserAccount();
		updatedUser.setUsername("testUser");
		updatedUser.setPassword("safeTest");

		when(userAccountService.updateUser(any(UserAccount.class))).thenReturn(updatedUser);
		
		mockMvc.perform(put("/users/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedUser)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(updatedUser)));
	}
	
	@Test
	public void testDeleteUserById() throws Exception {
		Long userID = 1L;
		
		doNothing().when(userAccountService).deleteUserById(userID);
		
		mockMvc.perform(delete("/users/delete/{id}", userID)).andExpect(status().isOk());
	}
	
}
