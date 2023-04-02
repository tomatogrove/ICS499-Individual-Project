package com.team4.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Chess {
	
	@Id
	@GeneratedValue
	private Long chessID;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "ChessPlayer",
			joinColumns = @JoinColumn(name = "chessID"),
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
	
	public Long getChessID() { return chessID; }
	public void setChessID(Long chessID) { this.chessID = chessID; }
	
	public List<Player> getPlayers() { return players; }
	public void setPlayers(List<Player> players) { this.players = players; }

	
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
