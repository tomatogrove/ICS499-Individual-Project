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

import com.team4.model.Hand;
import com.team4.repositories.HandRepository;

@RestController
@RequestMapping("/hand")
public class HandController {
	@Autowired
	private HandRepository handRepo;

	@GetMapping("/all")
	public List<Hand> list() {
		return handRepo.findAll();
	}

	@GetMapping("/{id}")
	public Hand get(@PathVariable Long id) {
		return handRepo.getReferenceById(id);
	}

	@PostMapping("/add")
	public Hand create(@RequestBody final Hand hand) {
		return handRepo.saveAndFlush(hand);
	}

	@PutMapping("/update")
	public Hand update(@RequestBody final Hand hand) {
		return handRepo.saveAndFlush(hand);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		handRepo.deleteById(id);
	}
}
