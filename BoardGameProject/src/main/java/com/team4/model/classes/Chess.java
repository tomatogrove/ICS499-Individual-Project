package com.team4.model.classes;

import com.backend.abstrct.Rule;
import com.team4.model.abstrct.Game;

public class Chess extends Game {
	private Board board;

	public Chess(String type, Player[] players, Rule[] rules) {
		super(type, players, rules);
		Board board = new Board();
	}
	
}
