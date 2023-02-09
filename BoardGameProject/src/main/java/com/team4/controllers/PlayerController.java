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

import com.team4.model.classes.Player;
import com.team4.repositories.PlayerRepository;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepo;
	
	
	@GetMapping("/all")
	public List<Player> list() {
		return playerRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Player get(@PathVariable Long id) {
		return playerRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Player create(@RequestBody final Player player) {
		return playerRepo.saveAndFlush(player);
	}
	
	@PutMapping("/update")
	public Player update(@RequestBody final Player player) {
		return playerRepo.saveAndFlush(player);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		playerRepo.deleteById(id);
	}
}
