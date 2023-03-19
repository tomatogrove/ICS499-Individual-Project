package com.team4.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Chess extends Game {
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "GamePlayer",
			joinColumns = @JoinColumn(name = "gameID"),
			inverseJoinColumns = @JoinColumn(name = "playerID"))
	private List<Player> players;
	

	@OneToOne
	private Board board;
	
	public Chess() { }

	public Chess(List<Player> players) {
		super();
		board = new Board();
		this.players = players;
	}
	
	public Board getBoard() { return board; }
	
	public void setBoard(Board board) { this.board = board; }
	
}
