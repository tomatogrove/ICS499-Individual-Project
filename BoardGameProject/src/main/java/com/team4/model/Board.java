package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import com.team4.model.Piece.Color;

@Entity
public class Board {

	@Id
	@GeneratedValue
	private Long boardID;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Space> spaces;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Piece> pieces;
	
	@OneToOne
	private Chess chess;

	public Board() {
		intializeSpaces();
		initializePieces();
	}

	private void intializeSpaces() {
		spaces = new ArrayList<>();
		
		Space space;
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				space = new Space(i, j);
				spaces.add(space);
				space.setBoard(this);
			}
		}
		
	}
	
	private void initializePieces() {
		
		pieces = new ArrayList<>();
		
		// can't really think of a better way to do this for now
		for (int i = 1; i < 9; i++) {
			// create the black and white pawns each iteration, have to assign the space too so it is not null
			Space space = getSpace(i, 2);
			Piece piece = new Pawn(Color.WHITE, space, this);
			assignPiece(space, piece);
			
			
			space = getSpace(i, 7);
			piece = new Pawn(Color.BLACK, space, this);
			assignPiece(space, piece);
			
			// create the four rooks, they only appear in the corners
			if (i == 1 || i == 8) {
				space = getSpace(i, 1);
				piece = new Rook(Color.WHITE, space, this);
				assignPiece(space, piece);
				
				space = getSpace(i, 8);
				piece = new Rook(Color.BLACK, space, this);
				assignPiece(space, piece);
			}
			
			
			// create the four knights
			if (i == 2 || i == 7) {
				space = getSpace(i, 1);
				piece = new Knight(Color.WHITE, space, this);
				assignPiece(space, piece);
				
				space = getSpace(i, 8);
				piece = new Knight(Color.BLACK, space, this);
				assignPiece(space, piece);
			}
			
			// create the four bishops
			if (i == 3 || i == 6) {
				space = getSpace(i, 1);
				piece = new Bishop(Color.WHITE, space, this);
				assignPiece(space, piece);
				
				space = getSpace(i, 8);
				piece = new Bishop(Color.BLACK, space, this);
				assignPiece(space, piece);
			}
			
			// create the two queens
			if (i == 4) {
				space = getSpace(i, 1);
				piece = new Queen(Color.WHITE, space, this);
				assignPiece(space, piece);
				
				space = getSpace(i, 8);
				piece = new Queen(Color.BLACK, space, this);
				assignPiece(space, piece);
			}
			
			// create the two kings
			if (i == 5) {
				space = getSpace(i, 1);
				piece = new King(Color.WHITE, space, this);
				assignPiece(space, piece);
				
				space = getSpace(i, 8);
				piece = new King(Color.BLACK, space, this);
				assignPiece(space, piece);
			}
		}
		
	}

	private void assignPiece(Space space, Piece piece) {
		space.setPiece(piece);
		space.setOccupied(true);
		pieces.add(piece);
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
	
	public Chess getChess() {
		return chess;
	}
	
	public void setChess(Chess chess) {
		this.chess = chess;
	}
	
	
	
	public List<Piece> getPieces(Piece.Color color, Piece.Type type) {
		List<Piece> pieces = new ArrayList<>();
		for (Piece piece: pieces) {
			if (piece.getType().equals(type) && piece.getColor().equals(color)) {
				pieces.add(piece);
			}
		}
		
		return pieces;
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
		if (x > 0 && x < 9 && y > 0 && y < 9) {	
			for (Space space: spaces) {
				if (space.getX() == x && space.getY() == y) {
					return space;
				}	
			}
		}
		
		return null;
	}

	
}
