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

import com.team4.model.classes.UserAccount;
import com.team4.repositories.UserAccountRepository;

@RestController
@RequestMapping("/users")
public class UserAccountController {

	@Autowired
	private UserAccountRepository userAccountRepo;
	
	
	@GetMapping("/all")
	public List<UserAccount> list() {
		return userAccountRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public UserAccount get(@PathVariable Long id) {
		return userAccountRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public UserAccount create(@RequestBody final UserAccount user) {
		return userAccountRepo.saveAndFlush(user);
	}
	
	@PutMapping("/update")
	public UserAccount update(@RequestBody final UserAccount user) {
		return userAccountRepo.saveAndFlush(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		userAccountRepo.deleteById(id);
	}
}
