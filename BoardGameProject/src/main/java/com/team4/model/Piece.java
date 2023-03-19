package com.team4.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToOne;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)  
public abstract class Piece {
	
	@Id
	@GeneratedValue
	protected Long pieceID;
	
	@Enumerated(EnumType.STRING)
	private Color color;
	
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Space currentSpace;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Board board;

	public Piece() {
	}

	public Piece(Color color, Type type, Space currentSpace, Board board) {
		this.color = color;
		this.type = type;
		this.currentSpace = currentSpace;
		this.board = board;
	}

	public Long getPieceID() { 
		return pieceID; 
	}
	public void setPieceID(Long pieceID) {
		this.pieceID = pieceID;
	}
	
	public abstract List<Space> findPossibleMoves();

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
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

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	
	public static enum Color {
		WHITE, BLACK
	}
	
	public static enum Type {
		PAWN, ROOK, KNIGHT, BISHOP, KING, QUEEN
	}
}