package com.team4.model.classes;

import java.util.List;

public abstract class Piece {
	
	private Color color;
	private Space currentSpace;

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