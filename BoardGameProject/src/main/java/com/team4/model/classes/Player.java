package com.team4.model.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.team4.model.abstrct.Game;

@Entity
public class Player extends User {

	@Id
	@GeneratedValue
	private int playerID;
	
	private Game[] activeGames;
	
	private Game[] gamesLost;
	
	private Game[] gamesWon;
	
	public Player(String username, String email, String password) {
		super(username, email, password);
	}
	
	public Player(String username, String email, String password, Game[] activeGames,
			Game[] gamesLost, Game[] gamesWon) {
		super(username, email, password);
		this.activeGames = activeGames;
		this.gamesLost = gamesLost;
		this.gamesWon = gamesWon;
	}

	public int getPlayerID() { return playerID; }
	public void setPlayerID(int playerID) { this.playerID = playerID; }
	
	public Game[] getActiveGames() { return activeGames; }
	public void setActiveGames(Game[] activeGames) { this.activeGames = activeGames; }
	
	public Game[] getGamesLost() { return gamesLost; }
	public void setGamesLost(Game[] gamesLost) { this.gamesLost = gamesLost; }
	
	public Game[] getGamesWon() { return gamesWon; }
	public void setGamesWon(Game[] gamesWon) { this.gamesWon = gamesWon; }

}
