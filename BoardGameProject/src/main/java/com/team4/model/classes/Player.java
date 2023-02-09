package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.team4.model.abstrct.Game;

@Entity
public class Player extends User {

	@Id
	@GeneratedValue
	private Long playerID;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "Game",
			joinColumns = @JoinColumn(name = "playerID"),
			inverseJoinColumns = @JoinColumn(name = "gameID"))
	private List<Game> activeGames;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "Game",
			joinColumns = @JoinColumn(name = "playerID"),
			inverseJoinColumns = @JoinColumn(name = "gameID"))
	private List<Game> gamesLost;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "Game",
			joinColumns = @JoinColumn(name = "playerID"),
			inverseJoinColumns = @JoinColumn(name = "gameID"))
	private List<Game> gamesWon;
	
	public Player() {
		super();
	}
	
	public Player(String username, String email, String password) {
		super(username, email, password);
		activeGames = new ArrayList<>();
		gamesLost = new ArrayList<>();
		gamesWon = new ArrayList<>();
	}
	
	public Player(String username, String email, String password, List<Game> activeGames,
			List<Game> gamesLost, List<Game> gamesWon) {
		super(username, email, password);
		this.activeGames = activeGames;
		this.gamesLost = gamesLost;
		this.gamesWon = gamesWon;
	}

	public Long getPlayerID() { return playerID; }
	public void setPlayerID(Long playerID) { this.playerID = playerID; }
	
	public List<Game> getActiveGames() { return activeGames; }
	public void setActiveGames(List<Game> activeGames) { this.activeGames = activeGames; }
	
	public List<Game> getGamesLost() { return gamesLost; }
	public void setGamesLost(List<Game> gamesLost) { this.gamesLost = gamesLost; }
	
	public List<Game> getGamesWon() { return gamesWon; }
	public void setGamesWon(List<Game> gamesWon) { this.gamesWon = gamesWon; }

}
