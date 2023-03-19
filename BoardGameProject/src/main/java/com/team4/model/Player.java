package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
public class Player extends UserAccount {
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "GamePlayer",
			joinColumns = @JoinColumn(name = "playerID"),
			inverseJoinColumns = @JoinColumn(name = "gameID"))
	private List<Game> games;

	
	public Player() {
		super();
	}
	
	public Player(String username, String email, String password) {
		super(username, email, password);
		games = new ArrayList<>();
	}
	
	public Player(String username, String email, String password, List<Game> activeGames,
			List<Game> gamesLost, List<Game> gamesWon) {
		super(username, email, password);
	}
	
	public List<Game> getGames() { return games; }
	public void setGames(List<Game> games) { this.games = games; }
	
	public List<Game> findGamesByStatus(Game.Status status) {
		List<Game> gamesWithStatus = new ArrayList<>();
		
		for (Game game : games) {
			if (game.getStatus().equals(status)) {
				gamesWithStatus.add(game);
			}
		}
		
		return gamesWithStatus;
	}

}
