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

import com.team4.model.User;
import com.team4.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping("/all")
	public List<User> list() {
		return userRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public User get(@PathVariable Long id) {
		return userRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public User create(@RequestBody final User user) {
		return userRepo.saveAndFlush(user);
	}
	
	@PutMapping("/update")
	public User update(@RequestBody final User user) {
		return userRepo.saveAndFlush(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		userRepo.deleteById(id);
	}
}
