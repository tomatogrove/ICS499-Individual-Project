package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.UserAccount;
import com.team4.repositories.UserAccountRepository;

@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepo;
	
	public UserAccountService(UserAccountRepository userAccountRepo) {
		this.userAccountRepo = userAccountRepo;
	}
	
	public UserAccount createUser(UserAccount user) {
		return userAccountRepo.saveAndFlush(user);
	}
	
	public List<UserAccount> getAllUsers() {
		return userAccountRepo.findAll();
	}
	
	public UserAccount getUserById(Long id) {
		return userAccountRepo.getReferenceById(id);
	}
	
	public UserAccount updateUser(UserAccount user) {
		return userAccountRepo.saveAndFlush(user);
	}
	
	public void deleteUserById(Long id) {
		userAccountRepo.deleteById(id);
	}
}
