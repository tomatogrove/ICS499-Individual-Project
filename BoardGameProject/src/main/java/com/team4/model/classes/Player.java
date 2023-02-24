package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.team4.model.abstrct.Game;

@Entity
public class Player extends User {
	
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
	
	public List<Game> getGamesByStatus(Game.Status status) {
		List<Game> activeGames = new ArrayList<>();
		
		for (Game game : games) {
			if (game.getStatus().equals(status)) {
				activeGames.add(game);
			}
		}
		
		return activeGames;
	}

}
