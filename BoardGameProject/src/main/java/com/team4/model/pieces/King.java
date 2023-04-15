package com.team4.model.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.team4.model.Board;
import com.team4.model.Space;
import jakarta.persistence.Entity;


@Entity
public class King extends Piece {
	
	private boolean hasMoved;
	
	public King() {
		setHasMoved(false);
	}
	
    public King(Color color, Space currentSpace, Board board) {
        super(color, Type.KING ,currentSpace, board);
    }

    
//    TODO castling
//    TODO infinite loop with enemy king
//    TODO pawn diagonals
    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();

        Board board = getBoard();
        int x = getCurrentSpace().getX();
        int y = getCurrentSpace().getY();

        Color enemyColor = getColor() == Color.BLACK ? Color.WHITE : Color.BLACK;
        List<Piece> enemyPieces = board.findPiecesByColor(enemyColor);

        Set<Space> enemyMoves = new HashSet<>();
        for (Piece enemyPiece : enemyPieces) {
        	if (!enemyPiece.getType().equals(getType())) {        		
        		List<Space> enemyPossibleMoves = enemyPiece.findPossibleMoves();
        		if (enemyPossibleMoves.size() > 0) {        		
        			enemyMoves.addAll(enemyPossibleMoves);
        		}
        	}
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) { continue; }

                Space space = board.findSpace(x + i, y + j);
                if (space == null || enemyMoves.contains(space)) { continue; }

                if (!space.isOccupied() || (space.isOccupied() && space.getPiece().getColor().equals(enemyColor))) {
                    possibleMoves.add(space);
                }
            }
        }

        return possibleMoves;
    }
//    @Override
//    public List<Space> findPossibleMoves() {
//        List<Space> possibleMoves = new ArrayList<>();
//		Board board = getBoard();
//		Color enemyColor = getColor() == Color.BLACK ? Color.WHITE : Color.BLACK;
//		List<Piece> enemyPieces = board.findPiecesByColor(enemyColor);
//		int x = getCurrentSpace().getX();
//		int y = getCurrentSpace().getY();
//		
//		if (!hasMoved) {
//			List<Piece> rooks = board.findPieces(getColor(), Type.ROOK);
//			for (Piece rook : rooks) {
//				if (rook != null && rook.getType() == Type.ROOK && !((King) rook).getHasMoved()) {
//					possibleMoves.add(rook.getCurrentSpace());
//				}
//			}
//		}
//		
//		Set<Space> enemyMoves = new HashSet<>();
//		for (Piece enemyPiece : enemyPieces) {
//			enemyMoves.addAll(enemyPiece.findPossibleMoves());
//		}
//		
//		
//		for (int i = -1; i < 2; i++) {
//			for (int j = -1; j < 2; j++) {
//				
//				if (i == 0 && j == 0) { continue; }
//				
//				Space space = board.findSpace(x + i, y + j);
//				
//				if (space == null) { continue; }			
//				
//				if (!space.getOccupied() || (space.getOccupied() && !space.getPiece().getColor().equals(enemyColor))) {
//					if (!enemyMoves.contains(space)) {
//						possibleMoves.add(space);
//					}
//				}
//				
//			}
//		}
//		
//        return possibleMoves;
//    }

	public boolean getHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
}
