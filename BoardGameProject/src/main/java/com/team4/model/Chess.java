package com.team4.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.team4.model.util.UserAccount;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Chess {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long chessID;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userAccountID")
	@ManyToOne()
	@JoinTable(
			name = "ChessWhiteUserAccount",
			joinColumns = @JoinColumn(name = "chessID"),
			inverseJoinColumns = @JoinColumn(name = "userAccountID"))
	private UserAccount whitePlayer;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userAccountID")
	@ManyToOne()
	@JoinTable(
			name = "ChessBlackUserAccount",
			joinColumns = @JoinColumn(name = "chessID"),
			inverseJoinColumns = @JoinColumn(name = "userAccountID"))
	private UserAccount blackPlayer;
	
	@JsonManagedReference(value="board-chess")
	@OneToOne(cascade=CascadeType.ALL)
	private Board board;
	
	public Chess() {
		board = new Board(this);
	}

	public Chess(UserAccount player) {
		board = new Board(this);

		this.whitePlayer = player;
		this.whitePlayer.addToChessList(this);
	}

	public Board getBoard() { return board; }
	
	public void setBoard(Board board) { this.board = board; }
	
	public Long getChessID() { return chessID; }
	public void setChessID(Long chessID) { this.chessID = chessID; }

	public UserAccount getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(UserAccount whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public UserAccount getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(UserAccount blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isUserInGame(UserAccount user){
		return user.equals(whitePlayer) || user.equals(blackPlayer);
	}

	public boolean needsPlayer(){
		return whitePlayer == null || blackPlayer == null;
	}

	public enum Status {
		ACTIVE,
		LOST,
		WON
	}
}
