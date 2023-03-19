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

import com.team4.model.Player;
import com.team4.services.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired 
	private PlayerService playerService;
	
	@GetMapping("/all")
	public List<Player> list() {
		return playerService.getAllPlayers();
	}
	
	@GetMapping("/{id}")
	public Player get(@PathVariable Long id) {
		return playerService.getPlayerById(id);
	}
	
	@PostMapping("/add")
	public Player create(@RequestBody final Player player) {
		return playerService.createPlayer(player);
	}
	
	@PutMapping("/update")
	public Player update(@RequestBody final Player player) {
		return playerService.updatePlayer(player);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		playerService.deletePlayerById(id);
	}

}
