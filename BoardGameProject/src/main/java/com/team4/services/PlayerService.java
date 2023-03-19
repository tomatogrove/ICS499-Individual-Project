package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Player;
import com.team4.repositories.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepo;
	
	public PlayerService(PlayerRepository playerRepo) {
		this.playerRepo = playerRepo;
	}
	
	public Player createPlayer(Player player) {
		return playerRepo.saveAndFlush(player);
	}
	
	public List<Player> getAllPlayers() {
		return playerRepo.findAll();
	}
	
	public Player getPlayerById(Long id) {
		return playerRepo.getReferenceById(id);
	}
	
	public Player updatePlayer(Player player) {
		return playerRepo.saveAndFlush(player);
	}
	
	public void deletePlayerById(Long id) {
		playerRepo.deleteById(id);
	}

}
