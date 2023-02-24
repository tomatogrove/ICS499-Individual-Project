package com.team4.model.classes.chess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.team4.model.abstrct.Piece;
import com.team4.model.abstrct.Piece.Color;
import com.team4.model.classes.chess.pieces.Bishop;
import com.team4.model.classes.chess.pieces.King;
import com.team4.model.classes.chess.pieces.Knight;
import com.team4.model.classes.chess.pieces.Pawn;
import com.team4.model.classes.chess.pieces.Queen;
import com.team4.model.classes.chess.pieces.Rook;

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
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				spaces.add(new Space(i, j));
			}
		}
		
	}
	
	private void initializePieces() {
		
		for (int i = 0; i < 8; i++) {
			pieces.add(new Pawn(Color.BLACK, getSpace(i, 2)));
			pieces.add(new Pawn(Color.WHITE, getSpace(i, 7)));
		}
		
		pieces.add(new Rook(Color.BLACK, getSpace(1, 1)));
		pieces.add(new Rook(Color.BLACK, getSpace(8, 1)));
		pieces.add(new Rook(Color.WHITE, getSpace(1, 8)));
		pieces.add(new Rook(Color.WHITE, getSpace(8, 8)));
		
		pieces.add(new Knight(Color.BLACK, getSpace(2, 1)));
		pieces.add(new Knight(Color.BLACK, getSpace(7, 1)));
		pieces.add(new Knight(Color.WHITE, getSpace(2, 8)));
		pieces.add(new Knight(Color.WHITE, getSpace(7, 8)));
		
		pieces.add(new Bishop(Color.BLACK, getSpace(3, 1)));
		pieces.add(new Bishop(Color.BLACK, getSpace(6, 1)));
		pieces.add(new Bishop(Color.WHITE, getSpace(3, 8)));
		pieces.add(new Bishop(Color.WHITE, getSpace(6, 8)));
		
		pieces.add(new Queen(Color.BLACK, getSpace(4, 1)));
		pieces.add(new King(Color.BLACK, getSpace(5, 1)));
		pieces.add(new Queen(Color.WHITE, getSpace(4, 8)));
		pieces.add(new King(Color.WHITE, getSpace(5, 8)));
		
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
