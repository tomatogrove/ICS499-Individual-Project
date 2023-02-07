package com.backend.classes;

import com.backend.abstrct.Game;

public class Player extends User {

	private int playerID;
	private Game[] activeGames;
	private Game[] gamesLost;
	private Game[] gamesWon;
	
	public Player(int userID, String username, String email, String password) {
		super(userID, username, email, password);
	}
	
	public Player(int userID, String username, String email, String password, int playerID, Game[] activeGames,
			Game[] gamesLost, Game[] gamesWon) {
		super(userID, username, email, password);
		this.playerID = playerID;
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
