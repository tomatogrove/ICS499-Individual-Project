package com.team4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team4.model.UserAccount;
import com.team4.services.UserAccountService;

@RestController
@RequestMapping("/users")
public class UserAccountController {

	
	@Autowired 
	private UserAccountService userAccountService;
	
	@GetMapping("/all")
	public List<UserAccount> list() {
		return userAccountService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public UserAccount get(@PathVariable Long id) {
		return userAccountService.getUserById(id);
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
