package com.team4.model.abstrct;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.team4.model.classes.Player;
import com.team4.model.classes.Rule;

@Entity
public abstract class Game {

	@Id
	@GeneratedValue
	private Long gameID;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private List<Player> players;
	
	// @ManyToOne(cascade = CascadeType.ALL)
	private List<Rule> rules;
	
	
	public Game() {}
	
	public Game(List<Player> players, List<Rule> rules) {
		super();
		this.players = players;
		this.rules = rules;
	}

	public List<Player> getPlayers() { return players; }
	public void setPlayers(List<Player> players) { this.players = players; }

	public List<Rule> getRules() { return rules; }
	public void setRules(List<Rule> rules) { this.rules = rules; }
}

