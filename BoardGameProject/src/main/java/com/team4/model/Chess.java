package com.team4.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Chess {
	
	@Id
	@GeneratedValue
	private Long chessID;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userID")
	@ManyToMany()
	@JoinTable(
			name = "ChessPlayer",
			joinColumns = @JoinColumn(name = "chessID"),
			inverseJoinColumns = @JoinColumn(name = "playerID"))
	private List<Player> players;
	
	@JsonManagedReference(value="board-chess")
	@OneToOne(cascade=CascadeType.ALL)
	private Board board;
	
	public Chess() { }
	
	public Chess(List<Player> players) {
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
