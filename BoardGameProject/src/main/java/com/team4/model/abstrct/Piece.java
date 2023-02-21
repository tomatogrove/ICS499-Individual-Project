package com.team4.model.abstrct;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.team4.model.classes.Space;

@Entity
public abstract class Piece {
	
	private Color color;
	private Space currentSpace;
	
	@OneToOne
	private Space space;

	public Piece(Color color, Space currentSpace) {
		this.color = color;
		this.currentSpace = currentSpace;
	}

	public abstract List<Space> getPossibleMoves();

	public Color getColor() {
		return color;
	}

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}

	public enum Color {
		WHITE, BLACK
	}
}