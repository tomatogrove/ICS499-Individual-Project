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

import com.team4.model.Game;
import com.team4.services.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired 
	private GameService gameService;
	
	@GetMapping("/all")
	public List<Game> list() {
		return gameService.getAllGames();
	}
	
	@GetMapping("/{id}")
	public Game get(@PathVariable Long id) {
		return gameService.getGameById(id);
	}
	
	@PostMapping("/add")
	public Game create(@RequestBody final Game game) {
		return gameService.createGame(game);
	}
	
	@PutMapping("/update")
	public Game update(@RequestBody final Game game) {
		return gameService.updateGame(game);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		gameService.deleteGameById(id);
	}

}
