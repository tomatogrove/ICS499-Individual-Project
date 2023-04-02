package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


@Entity
public class Player extends UserAccount {
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "chessID")
	@ManyToMany()
	@JoinTable(
			name = "ChessPlayer",
			joinColumns = @JoinColumn(name = "playerID"),
			inverseJoinColumns = @JoinColumn(name = "chessID"))
	private List<Chess> chessList;

	
	public Player() {
		super();
	}
	
	public Player(String username, String email, String password) {
		super(username, email, password);
		chessList = new ArrayList<>();
	}
	
	public Player(String username, String email, String password, List<Chess> activeChessLists,
			List<Chess> chessLost, List<Chess> chessWon) {
		super(username, email, password);
	}
	
	public List<Chess> getChessList() { return chessList; }
	public void setChessList(List<Chess> chess) { this.chessList = chess; }
	
	public List<Chess> findChessListByStatus(Chess.Status status) {
		List<Chess> chessWithStatus = new ArrayList<>();
		
		for (Chess chess : chessList) {
			if (chess.getStatus().equals(status)) {
				chessWithStatus.add(chess);
			}
		}
		
		return chessWithStatus;
	}

}
