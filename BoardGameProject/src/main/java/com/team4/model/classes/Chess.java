package com.team4.model.classes;

import com.team4.model.abstrct.Game;

public class Chess extends Game {
	private Board board;

	public Chess(String type, Player[] players, Rule[] rules) {
		super();
		board = new Board();
	}
	
}
