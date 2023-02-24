package com.team4.model.classes.chess;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.team4.model.abstrct.Game;
import com.team4.model.classes.Player;
import com.team4.model.classes.Rule;

@Entity
public class Chess extends Game {
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "GamePlayer",
			joinColumns = @JoinColumn(name = "gameID"),
			inverseJoinColumns = @JoinColumn(name = "playerID"))
	private List<Player> players;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Rule> rules;
	

	@OneToOne
	private Board board;
	
	public Chess() { }

	public Chess(String type, List<Player> players, List<Rule> rules) {
		super();
		board = new Board();
		this.players = players;
		this.rules = rules;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public List<Rule> getRules() {
		return rules;
	}
	
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	
	public Board getBoard() { return board; }
	
	public void setBoard(Board board) { this.board = board; }
	
}
