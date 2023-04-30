package com.team6.model.pieces;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team6.model.Board;
import com.team6.model.Space;
import com.team6.model.enums.Color;
import com.team6.model.enums.Type;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Piece {
	
	@Id
	@GeneratedValue(strategy= GenerationType.TABLE)
	protected Long pieceID;
	
	@Enumerated(EnumType.STRING)
	private Color color;
	
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@JsonBackReference(value="piece-space")
	@OneToOne(cascade = CascadeType.ALL)
	private Space currentSpace;
	
	@JsonBackReference(value="piece-board")
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

	public static Piece createPiece(int x, int y, Space space, Board board) {
		Piece piece;
		Color color = y > 2 ? Color.BLACK : Color.WHITE;

		if (y == 2 || y == 7) {
			piece = new Pawn(color, space, board);
		} else {
			if (x == 1 || x == 8) {
				piece = new Rook(color, space, board);
			} else if (x == 2 || x == 7) {
				piece = new Knight(color, space, board);
			} else if (x == 3 || x == 6) {
				piece = new Bishop(color, space, board);
			} else if (x == 4) {
				piece = new Queen(color, space, board);
			} else {
				piece = new King(color, space, board);
			}
		}

		return piece;
	}
}