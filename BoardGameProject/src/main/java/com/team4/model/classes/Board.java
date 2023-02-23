package com.team4.model.classes;

import com.team4.model.abstrct.Piece;

public class Board {
	private Space[][] spaces;
	private Piece[] pieces;

	public Board() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                spaces[i][j] = new Space(i, j);
            }
        }
		
		
		
		
	}
	
}
