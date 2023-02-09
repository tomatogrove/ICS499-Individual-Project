package com.boardgame.model;

import java.util.List;

public abstract class Piece {
	
	private Color color;
	private Square currentSquare;

	public Piece(Color color, Square currentSquare) {
		this.color = color;
		this.currentSquare = currentSquare;
	}

	public abstract List<Square> getPossibleMoves();

	public Color getColor() {
		return color;
	}

	public Square getCurrentSquare() {
		return currentSquare;
	}

	public void setCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
	}

	public enum Color {
		WHITE, BLACK
	}
}