package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Game;
import com.team4.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepo;
	
	public GameService(GameRepository gameRepo) {
		this.gameRepo = gameRepo;
	}
	
	public Game createGame(Game game) {
		return gameRepo.saveAndFlush(game);
	}
	
	public List<Game> getAllGames() {
		return gameRepo.findAll();
	}
	
	public Game getGameById(Long id) {
		return gameRepo.getReferenceById(id);
	}
	
	public Game updateGame(Game game) {
		return gameRepo.saveAndFlush(game);
	}
	
	public void deleteGameById(Long id) {
		gameRepo.deleteById(id);
	}
}
