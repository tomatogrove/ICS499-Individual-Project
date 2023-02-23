package com.team4.model.classes;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.team4.model.abstrct.Game;

@Entity
public class Chess extends Game {
	
	@OneToOne
	private Board board;

	public Chess(String type, Player[] players, Rule[] rules) {
		super();
		board = new Board();
	}
	
	public Board getBoard() { return board; }
	
	public void setBoard(Board board) { this.board = board; }
	
}
