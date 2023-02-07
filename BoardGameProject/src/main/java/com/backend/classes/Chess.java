package com.backend.classes;

import com.backend.abstrct.Game;
import com.backend.abstrct.Rule;

public class Chess extends Game {
	private Board board;

	public Chess(String type, Player[] players, Rule[] rules) {
		super(type, players, rules);
		Board board = new Board();
	}
	
}
