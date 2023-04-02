package com.team4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Entity;


@Entity
public class King extends Piece {
	
	private boolean hasMoved;
	
	public King() {
		setHasMoved(false);
	}
	
    public King(Color color, Space currentSpace, Board board) {
        super(color, Piece.Type.KING ,currentSpace, board);
    }

    
    // keep castling in mind and preventing check
    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
		Board board = getBoard();
		Color enemyColor = getColor() == Color.BLACK ? Color.WHITE : Color.BLACK;
		List<Piece> enemyPieces = board.getPiecesByColor(enemyColor);
		int x = getCurrentSpace().getX();
		int y = getCurrentSpace().getY();
		
		if (!hasMoved) {
			List<Piece> rooks = board.getPieces(getColor(), Type.ROOK);
			for (Piece rook : rooks) {
				if (rook != null && rook.getType() == Type.ROOK && !((King) rook).getHasMoved()) {
					possibleMoves.add(rook.getCurrentSpace());
				}
			}
		}
		
		Set<Space> enemyMoves = new HashSet<>();
		for (Piece enemyPiece : enemyPieces) {
			enemyMoves.addAll(enemyPiece.findPossibleMoves());
		}
		
		
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				
				if (i == 0 && j == 0) { continue; }
				
				Space space = board.getSpace(x + i, y + j);
				
				if (space == null) { continue; }			
				
				if (!space.getOccupied() || (space.getOccupied() && !space.getPiece().getColor().equals(enemyColor))) {
					if (!enemyMoves.contains(space)) {
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
