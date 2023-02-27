package com.team4.model.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.team4.model.abstrct.Piece;

@Entity
public class Board {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@OneToMany
	private Space[][] spaces;
	
	@OneToMany
	private Piece[] pieces;

	public Board() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                spaces[i][j] = new Space(i, j);
            }
        }
	}
	
	
}
