package com.backend.abstrct;

import com.backend.classes.Player;
import com.backend.classes.Rule;

public abstract class Game {

	private String type;
	private Player[] players;
	private Rule[] rules;
	
	public Game() {}
	
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	public Player[] getPlayers() { return players; }
	public void setPlayers(Player[] players) { this.players = players; }

	public Rule[] getRules() { return rules; }
	public void setRules(Rule[] rules) { this.rules = rules; }
}

