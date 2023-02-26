package com.team4.model.abstrct;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.team4.model.classes.Player;
import com.team4.model.classes.Rule;

@MappedSuperclass
public abstract class Game {

	@Id
	@GeneratedValue
	private Long gameID;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "GamePlayer",
			joinColumns = @JoinColumn(name = "gameID"),
			inverseJoinColumns = @JoinColumn(name = "playerID"))
	private List<Player> players;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "GameRule",
			joinColumns = @JoinColumn(name = "gameID"),
			inverseJoinColumns = @JoinColumn(name = "ruleID"))
	private List<Rule> rules;
	
	private Status status;
	
	public Game() {}
	
	public Game(List<Player> players, List<Rule> rules) {
		super();
		this.players = players;
		this.rules = rules;
	}

	public Long getGameID() { return gameID; }
	public void setGameID(Long gameID) { this.gameID = gameID; }
	
	public List<Player> getPlayers() { return players; }
	public void setPlayers(List<Player> players) { this.players = players; }

	public List<Rule> getRules() { return rules; }
	public void setRules(List<Rule> rules) { this.rules = rules; }
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		ACTIVE,
		LOST,
		WON
	}
}

