package com.team4.model.abstrct;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.team4.model.classes.Player;
import com.team4.model.classes.Rule;

@Entity
public abstract class Game {

	@Id
	@GeneratedValue
	private int gameID;
	
	private String type;
	
	private Player[] players;
	
	private Rule[] rules;
	
	public Game(String type, Player[] players, Rule[] rules) {
		super();
		this.type = type;
		this.players = players;
		this.rules = rules;
	}
	
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	public Player[] getPlayers() { return players; }
	public void setPlayers(Player[] players) { this.players = players; }

	public Rule[] getRules() { return rules; }
	public void setRules(Rule[] rules) { this.rules = rules; }
}

