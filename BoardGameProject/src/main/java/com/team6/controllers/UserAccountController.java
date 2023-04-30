package com.team6.controllers;

import com.team6.model.util.UserAccount;
import com.team6.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserAccountController {

	
	@Autowired 
	private UserAccountService userAccountService;

	@GetMapping("/{id}")
	public UserAccount get(@PathVariable Long id) {
		return userAccountService.getUserById(id);
	}

	@GetMapping("/user")
	public UserAccount getUserByCredentials(@RequestBody final UserAccount user) {
		return userAccountService.checkCredentials(user);
	}
	
	@PostMapping("/add")
	public UserAccount create(@RequestBody final UserAccount user) {
		return userAccountService.createUser(user);
	}
	
	@PutMapping("/update")
	public UserAccount update(@RequestBody final UserAccount user) {
		return userAccountService.updateUser(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		userAccountService.deleteUserById(id);
	}
	
}
