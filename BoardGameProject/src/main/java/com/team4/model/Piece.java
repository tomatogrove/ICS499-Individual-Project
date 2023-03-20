package com.team4.model;

import java.util.ArrayList;
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
	
	// for Bishop and half of Queen movement options
	// in those class's getPossibleMoves() call this method 
	public List<Space> getPossibleDiagonalMoves() {
        List<Space> possibleMoves = new ArrayList<>();
		Board board = getBoard();
		final int x = getCurrentSpace().getX();
		final int y = getCurrentSpace().getY();
		
		// used for moving through spaces of the board, reset in new direction...
		// before each while loop below
		int workingX = x + 1;
		int workingY = y + 1;
		
		// diagonal towards H8 (8,8)
		while (workingX < 9 && workingY < 9) {
			Space space = board.getSpace(workingX, workingY);
			// check if space is empty first, else we need to check what piece is there
			if (space.getPiece().equals(null)) {
				// adding the empty space as possible move and incrementing for next space to check
				possibleMoves.add(space);
				workingX++;
				workingY++;
			} else {
				// if the piece is the not same color we can add it to the possible moves
				// regardless this direction is finished, so we break
				if (!space.getPiece().getColor().equals(getColor())) {
					possibleMoves.add(space);
				}
				break;
			}
		}
		
		workingX = x - 1;
		workingY = y + 1;
		
		// diagonal towards A8 (1,8)
		while (workingX > 0 && workingY < 9) {
			Space space = board.getSpace(workingX, workingY);
			// check if space is empty first, else we need to check what piece is there
			if (space.getPiece().equals(null)) {
				// adding the empty space as possible move and incrementing for next space to check
				possibleMoves.add(space);
				workingX--;
				workingY++;
			} else {
				// if the piece is the not same color we can add it to the possible moves
				// regardless this direction is finished, so we break
				if (!space.getPiece().getColor().equals(getColor())) {
					possibleMoves.add(space);
				}
				break;
			}
		}
		
		workingX = x - 1;
		workingY = y - 1;
		
		// diagonal towards A1 (1,1)
		while (workingX > 0 && workingY > 0) {
			Space space = board.getSpace(workingX, workingY);
			// check if space is empty first, else we need to check what piece is there
			if (space.getPiece().equals(null)) {
				// adding the empty space as possible move and incrementing for next space to check
				possibleMoves.add(space);
				workingX--;
				workingY--;
			} else {
				// if the piece is the not same color we can add it to the possible moves
				// regardless this direction is finished, so we break
				if (!space.getPiece().getColor().equals(getColor())) {
					possibleMoves.add(space);
				}
				break;
			}
		}
		
		workingX = x + 1;
		workingY = y - 1;
		
		// diagonal towards H1 (8,1)
		while (workingX < 9 && workingY > 0) {
			Space space = board.getSpace(workingX, workingY);
			// check if space is empty first, else we need to check what piece is there
			if (space.getPiece().equals(null)) {
				// adding the empty space as possible move and incrementing for next space to check
				possibleMoves.add(space);
				workingX++;
				workingY--;
			} else {
				// if the piece is the not same color we can add it to the possible moves
				// regardless this direction is finished, so we break
				if (!space.getPiece().getColor().equals(getColor())) {
					possibleMoves.add(space);
				}
				break;
			}
		}
		
        return possibleMoves;
    }
	
	// for Rook and half of Queen movement options
	// in those class's getPossibleMoves() call this method 
	public List<Space> getPossibleLinearMoves() {
		List<Space> possibleMoves = new ArrayList<>();
		Board board = getBoard();
		final int x = getCurrentSpace().getX();
		final int y = getCurrentSpace().getY();

		// used for moving through spaces of the board, reset in new direction...
		// before each while loop below
		int workingX = x;
		int workingY = y + 1;
		
		// positive vertical towards x8 (x,8)
		while (workingY < 9) {
			Space space = board.getSpace(workingX, workingY);
			// check if space is empty first, else we need to check what piece is there
			if (space.getPiece().equals(null)) {
				// adding the empty space as possible move and incrementing for next space to check
				possibleMoves.add(space);
				workingY++;
			} else {
				// if the piece is the not same color we can add it to the possible moves
				// regardless this direction is finished, so we break
				if (!space.getPiece().getColor().equals(getColor())) {
					possibleMoves.add(space);
				}
				break;
			}
		}
		
		workingX = x;
		workingY = y - 1;
		
		// negative vertical towards x1 (x,1)
		while (workingY > 0) {
			Space space = board.getSpace(workingX, workingY);
			// check if space is empty first, else we need to check what piece is there
			if (space.getPiece().equals(null)) {
				// adding the empty space as possible move and incrementing for next space to check
				possibleMoves.add(space);
				workingY--;
			} else {
				// if the piece is the not same color we can add it to the possible moves
				// regardless this direction is finished, so we break
				if (!space.getPiece().getColor().equals(getColor())) {
					possibleMoves.add(space);
				}
				break;
			}
		}
		
		workingX = x + 1;
		workingY = y;
		
		// positive horizontal towards Hy (8,y)
		while (workingX < 9) {
			Space space = board.getSpace(workingX, workingY);
			// check if space is empty first, else we need to check what piece is there
			if (space.getPiece().equals(null)) {
				// adding the empty space as possible move and incrementing for next space to check
				possibleMoves.add(space);
				workingX++;
			} else {
				// if the piece is the not same color we can add it to the possible moves
				// regardless this direction is finished, so we break
				if (!space.getPiece().getColor().equals(getColor())) {
					possibleMoves.add(space);
				}
				break;
			}
		}
		
		workingX = x - 1;
		workingY = y;
		
		// negative horizontal towards Ay (1,y)
		while (workingX > 0) {
			Space space = board.getSpace(workingX, workingY);
			// check if space is empty first, else we need to check what piece is there
			if (space.getPiece().equals(null)) {
				// adding the empty space as possible move and incrementing for next space to check
				possibleMoves.add(space);
				workingX--;
			} else {
				// if the piece is the not same color we can add it to the possible moves
				// regardless this direction is finished, so we break
				if (!space.getPiece().getColor().equals(getColor())) {
					possibleMoves.add(space);
				}
				break;
			}
		}
		
		return possibleMoves;
	}
	
}