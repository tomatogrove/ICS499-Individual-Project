package com.team4.model.abstrct;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.team4.model.classes.chess.Space;

@MappedSuperclass
public abstract class Piece {
	
	protected Long pieceID;
	
	private Color color;
	private Type type;
	
	@OneToOne
	private Space currentSpace;

	public Piece() {
	}

	public Piece(Color color, Type type, Space currentSpace) {
		this.color = color;
		this.type = type;
		this.currentSpace = currentSpace;
	}

	public Long getPieceID() { 
		return pieceID; 
	}
	public void setPieceID(Long pieceID) {
		this.pieceID = pieceID;
	}
	
	public abstract List<Space> getPossibleMoves();

	public Color getColor() {
		return color;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
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
	
	public enum Type {
		PAWN, ROOK, KNIGHT, BISHOP, KING, QUEEN
	}
}