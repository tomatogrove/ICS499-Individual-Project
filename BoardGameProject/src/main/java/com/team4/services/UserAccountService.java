package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
	    UserAccount userAccount = getAllUsers().stream()
	            .filter((testUser) -> testUser.getEmail().equals(user.getEmail()) || testUser.getUsername().equals(user.getUsername()))
	            .findFirst().orElse(null);
	    
	    if (userAccount == null) {
	    	return userAccountRepo.saveAndFlush(user);
		}
	    
	    if (userAccount.getEmail().equals(user.getEmail()) && userAccount.getUsername().equals(user.getUsername())){
	    	user.setUserID(-1L);
	    } else if (userAccount.getEmail().equals(user.getEmail())) {
	    	user.setUserID(-2L);
	    } else if (userAccount.getUsername().equals(user.getUsername())) {
	    	user.setUserID(-3L);	    	
	    }
	    return user;
	}
	
	public List<UserAccount> getAllUsers() {
		return userAccountRepo.findAll();
	}
	
	public UserAccount getUserById(Long id) {
		return userAccountRepo.findById(id).get();
	}
	
	public UserAccount updateUser(UserAccount user) {
		return userAccountRepo.saveAndFlush(user);
	}
	
	public void deleteUserById(Long id) {
		userAccountRepo.deleteById(id);
	}

	public UserAccount checkCredentials(UserAccount user) {
		return userAccountRepo.findOne(Example.of(user)).orElse(null);
	}

	public UserAccount findUserByEmailAndPassword(String email, String password) {
		return userAccountRepo.findUserAccountByEmailAndPassword(email, password).orElse(null);
	}
}
