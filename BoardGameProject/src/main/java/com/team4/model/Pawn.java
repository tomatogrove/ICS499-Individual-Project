package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;


@Entity
public class Pawn extends Piece {
	
	private boolean hasMoved;
	
	public Pawn() {
		hasMoved = false;
	}
	
    public Pawn(Color color, Space currentSpace, Board board) {
        super(color, Piece.Type.PAWN, currentSpace, board);
    }
    
    @Override
	public List<Space> findPossibleMoves() {
		List<Space> possibleMoves = new ArrayList<>();
		Board board = getBoard();
		int x = getCurrentSpace().getX();
		int y = getCurrentSpace().getY();
		
		// movement of pawns is opposite (regarding vertical movement) for each color... white's logic first
		if (getColor().equals(Color.WHITE)) {
			// if it is the first move, and the positions one and two spaces ahead are not occupied, add it as a possible move
			if (!hasMoved && !board.getSpace(x, y + 1).getOccupied() && !board.getSpace(x, y + 2).getOccupied()) {
				possibleMoves.add(board.getSpace(x, y + 2));
			}

			for (int i = -1; i < 2; i++) {
				Space space = board.getSpace(x + i, y + 1);
				// ensure that the space exists on the board before adding it to the possible moves
				if (space != null) {
					// if the space is a diagonal and it is occupied by an enemy color, then add it as a possible move (capture)
					if (space.getX() != x && space.getOccupied() && !space.getPiece().getColor().equals(getColor())) {
						possibleMoves.add(space);

					//otherwise if the space is not occupied it is viable
					} else if (space.getX() == x && !space.getOccupied()) {
						possibleMoves.add(space);
					}
				}
			}
		} else {
			// if it is the first move, and the positions one and two spaces ahead are not occupied, add it as a possible move
			if (!hasMoved && !board.getSpace(x, y - 1).getOccupied() && !board.getSpace(x, y - 2).getOccupied()) {
				possibleMoves.add(board.getSpace(x, y - 2));
			}

			for (int i = -1; i < 2; i++) {
				Space space = board.getSpace(x + i, y - 1);
				// ensure that the space exists on the board before adding it to the possible moves
				if (space != null) {
					// if the space is a diagonal and it is occupied by an enemy color, then add it as a possible move (capture)
					if (space.getX() != x && space.getOccupied() && !space.getPiece().getColor().equals(getColor())) {
						possibleMoves.add(space);

					//otherwise if the space is not occupied it is viable
					} else if (space.getX() == x && !space.getOccupied()) {
						possibleMoves.add(space);
					}
				}
			}
		}
		
		return possibleMoves;
	}

	public boolean getHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

}
