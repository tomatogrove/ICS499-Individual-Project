package com.ics499.team4.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.team4.model.UserAccount;
import com.team4.repositories.UserAccountRepository;
import com.team4.services.UserAccountService;

@RunWith(MockitoJUnitRunner.class)
public class UserAccountServiceUnitTest {
	
	@Mock
	private UserAccountRepository userAccountRepo;
	
	@InjectMocks
	private UserAccountService userAccountService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddValidUserAccount() {
		UserAccount mockUserAccount = new UserAccount();
		mockUserAccount.setUsername("testUserAccount");
		mockUserAccount.setPassword("testPass");
		
		when(userAccountRepo.saveAndFlush(any(UserAccount.class))).thenReturn(mockUserAccount);
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername("testingUserAccount");
		userAccount.setPassword("testing");
		
		UserAccount resultUserAccount = userAccountService.createUser(userAccount);
		
		
		assertEquals("testUserAccount", resultUserAccount.getUsername());
		assertEquals("testPass", resultUserAccount.getPassword());
	}
	

	@Test
	public void testGetAllUserAccounts() {
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername("testUserAccount");
		userAccount.setEmail("testUserAccount@email.com");
		userAccount.setPassword("testPass");
		
		UserAccount userAccount2 = new UserAccount();
		userAccount2.setUsername("testUserAccount2");
		userAccount2.setEmail("testUserAccount2@email.com");
		userAccount2.setPassword("testPass");
		
		List<UserAccount> mockUserAccounts = new ArrayList<>();
		mockUserAccounts.add(userAccount);
		mockUserAccounts.add(userAccount2);
		
		when(userAccountRepo.findAll()).thenReturn(mockUserAccounts);
		
		
		List<UserAccount> resultUserAccounts = userAccountService.getAllUsers();
		
		assertEquals(mockUserAccounts, resultUserAccounts);
	}
	
	@Test
	public void testGetUserAccountByID() {
		Long userAccountID = 1L;
		UserAccount savedUserAccount = new UserAccount();
		savedUserAccount.setUsername("testUserAccount");
		savedUserAccount.setPassword("testPass");
		
		when(userAccountRepo.getReferenceById(userAccountID)).thenReturn(savedUserAccount);
		
		UserAccount resultUserAccount = userAccountService.getUserById(userAccountID);
		
		assertEquals("testUserAccount", resultUserAccount.getUsername());
		assertEquals("testPass", resultUserAccount.getPassword());
	}
	
	@Test
	public void testUpdateUserAccount() {
		UserAccount updateUserAccount = new UserAccount();
		updateUserAccount.setUsername("testUserAccount");
		updateUserAccount.setPassword("testPass");

		when(userAccountRepo.saveAndFlush(any(UserAccount.class))).thenReturn(updateUserAccount);
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername("testUserAccounts");
		userAccount.setPassword("testPassers");
		
		UserAccount resultUserAccount = userAccountService.updateUser(userAccount);
		
		assertEquals("testUserAccount", resultUserAccount.getUsername());
		assertEquals("testPass", resultUserAccount.getPassword());
	}
	
	@Test
	public void testDeleteUserAccountById() throws Exception {
		Long userAccountID = 1L;
		
		doNothing().when(userAccountRepo).deleteById(userAccountID);
		
		userAccountService.deleteUserById(userAccountID);
		
		verify(userAccountRepo, times(1)).deleteById(anyLong());
	}

}
