package com.team4.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.team4.model.pieces.Piece;
import com.team4.model.util.UserAccount;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Chess {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long chessID;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	private Long whitePlayerID;
	private Long blackPlayerID;
	private Long winnerID;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "whitePlayerID", updatable = false, insertable = false)
	private UserAccount whitePlayer;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blackPlayerID", updatable = false, insertable = false)
	private UserAccount blackPlayer;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "winnerID", updatable = false, insertable = false)
	private UserAccount winner;
	
	@JsonManagedReference(value="board-chess")
	@OneToOne(cascade=CascadeType.ALL)
	private Board board;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate startedDate;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate lastPlayed;
	
	public Chess() {
		board = new Board(this);
		startedDate = LocalDate.now();
		lastPlayed = LocalDate.now();
	}

	public Chess(UserAccount player) {
		board = new Board(this);
		startedDate = LocalDate.now();
		lastPlayed = LocalDate.now();
		this.whitePlayerID = player.getUserAccountID();
	}

	public Board getBoard() { return board; }
	
	public void setBoard(Board board) { this.board = board; }
	
	public Long getChessID() { return chessID; }
	public void setChessID(Long chessID) { this.chessID = chessID; }

	public Long getWhitePlayerID() { return whitePlayerID; }

	public void setWhitePlayerID(Long whitePlayerID) { this.whitePlayerID = whitePlayerID; }

	public Long getBlackPlayerID() { return blackPlayerID; }

	public void setBlackPlayerID(Long blackPlayerID) { this.blackPlayerID = blackPlayerID; }

	public Long getWinnerID() { return winnerID; }

	public void setWinnerID(Long winnerID) { this.winnerID = winnerID; }

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

	public UserAccount getWinner() { return winner; }

	public void setWinner(UserAccount winner) { this.winner = winner; }

	public void setWinnerByColor(Piece.Color color) {
		UserAccount winner = color.equals(Piece.Color.BLACK) ? blackPlayer : whitePlayer;
		setWinnerID(winner.getUserAccountID());
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isUserInGame(UserAccount user){
		return user.getUserAccountID().equals(whitePlayerID) || user.getUserAccountID().equals(blackPlayerID);
	}

	public boolean needsPlayer(){
		return whitePlayerID == null || blackPlayerID == null;
	}

	public void setStartedDate(LocalDate startedDate) { this.startedDate = startedDate; }

	public LocalDate getStartedDate() { return startedDate; }

	public LocalDate getLastPlayed() { return lastPlayed; }

	public void setLastPlayed(LocalDate lastPlayed) { this.lastPlayed = lastPlayed; }

	public enum Status {
		ACTIVE,
		DONE
	}
}
