package com.team4.model.classes;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="piece")
public abstract class Piece {
	
	@Id
	@GeneratedValue
	private Long pieceID;
	private Color color;
	private Space currentSpace;
	
	public Piece() {
		super();
	}

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