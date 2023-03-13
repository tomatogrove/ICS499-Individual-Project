package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.team4.model.Piece.Color;

@Entity
public class Board {

	@Id
	@GeneratedValue
	private Long boardID;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Space> spaces;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Piece> pieces;

	public Board() {
		intializeSpaces();
		initializePieces();
	}

	private void intializeSpaces() {
		spaces = new ArrayList<>();
		
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				spaces.add(new Space(i, j));
			}
		}
		
	}
	
	private void initializePieces() {
		
		pieces = new ArrayList<>();
		
		// can't really think of a better way to do this for now
		for (int i = 1; i < 9; i++) {
			pieces.add(new Pawn(Color.BLACK, getSpace(i, 2), this));
			pieces.add(new Pawn(Color.WHITE, getSpace(i, 7), this));
			
			
			if (i == 1 || i == 8) {
				pieces.add(new Rook(Color.BLACK, getSpace(i, 1), this));
				pieces.add(new Rook(Color.WHITE, getSpace(i, 8), this));
			}
			
			
			if (i == 2 || i == 7) {
				pieces.add(new Knight(Color.BLACK, getSpace(i, 1), this));
				pieces.add(new Knight(Color.WHITE, getSpace(i, 8), this));
			}
			
			
			if (i == 3 || i == 6) {
				pieces.add(new Bishop(Color.BLACK, getSpace(i, 1), this));
				pieces.add(new Bishop(Color.WHITE, getSpace(i, 8), this));
			}
			
			if (i == 4) {
				pieces.add(new Queen(Color.BLACK, getSpace(i, 1), this));
				pieces.add(new Queen(Color.WHITE, getSpace(i, 8), this));
			}
			
			if (i == 5) {
				pieces.add(new King(Color.BLACK, getSpace(i, 1), this));
				pieces.add(new King(Color.WHITE, getSpace(i, 8), this));
			}
		}
		
	}
	
	public Long getBoardID() {
		return boardID;
	}
	
	public void setBoardID(Long boardID) {
		this.boardID = boardID;
	}
	
	public List<Space> getSpaces() {
		return spaces;
	}
	
	public void setSpaces(List<Space> spaces) {
		this.spaces = spaces;
	}
	
	public List<Piece> getPieces() {
		return pieces;
	}
	
	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}
	
	public Piece getPiece(Piece.Color color, Piece.Type type) {
		for (Piece piece: pieces) {
			if (piece.getType().equals(type) && piece.getColor().equals(color)) {
				return piece;
			}
		}
		
		return null;
	}
	
	public List<Piece> getPiecesByColor(Piece.Color color) {
		List<Piece> pieces = new ArrayList<>();
		
		for (Piece piece: this.pieces) {
			if (piece.getColor().equals(color)) {
				pieces.add(piece);
			}
		}
		
		return pieces;
	}
	
	public Space getSpace(int x, int y) {
		for (Space space: spaces) {
			if (space.getX() == x && space.getY() == y) {
				return space;
			}	
		}
		
		return null;
	}

	
}
