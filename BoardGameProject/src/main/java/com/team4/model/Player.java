package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
public class Player extends UserAccount {
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
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
	
	public Player(String username, String email, String password, List<Chess> activeChesss,
			List<Chess> chesssLost, List<Chess> chesssWon) {
		super(username, email, password);
	}
	
	public List<Chess> getChesss() { return chessList; }
	public void setChesss(List<Chess> chesss) { this.chessList = chesss; }
	
	public List<Chess> findChesssByStatus(Chess.Status status) {
		List<Chess> chesssWithStatus = new ArrayList<>();
		
		for (Chess chess : chessList) {
			if (chess.getStatus().equals(status)) {
				chesssWithStatus.add(chess);
			}
		}
		
		return chesssWithStatus;
	}

}
