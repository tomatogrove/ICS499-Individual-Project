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

import com.team4.model.classes.Card;
import com.team4.repositories.CardRepository;

@RestController
@RequestMapping("/uno")
public class CardController {
	
	@Autowired 
	private CardRepository cardRepo;
	
	@GetMapping("/all")
	public List<Card> list() {
		return cardRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Card get(@PathVariable Long id) {
		return cardRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Card create(@RequestBody final Card player) {
		return cardRepo.saveAndFlush(player);
	}
	
	@PutMapping("/update")
	public Card update(@RequestBody final Card player) {
		return cardRepo.saveAndFlush(player);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		cardRepo.deleteById(id);
	}

}
