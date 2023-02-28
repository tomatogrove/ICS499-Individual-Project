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

import com.team4.model.Rule;
import com.team4.repositories.RuleRepository;

@RestController
@RequestMapping("/rule")
public class RuleController {

	@Autowired 
	private RuleRepository ruleRepo;
	
	@GetMapping("/all")
	public List<Rule> list() {
		return ruleRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Rule get(@PathVariable Long id) {
		return ruleRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Rule create(@RequestBody final Rule rule) {
		return ruleRepo.saveAndFlush(rule);
	}
	
	@PutMapping("/update")
	public Rule update(@RequestBody final Rule rule) {
		return ruleRepo.saveAndFlush(rule);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		ruleRepo.deleteById(id);
	}
}
