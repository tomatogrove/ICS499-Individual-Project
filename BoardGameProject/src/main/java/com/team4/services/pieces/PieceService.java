package com.team4.services.pieces;

import java.util.List;

import com.team4.model.Chess;
import com.team4.services.BoardService;
import com.team4.services.ChessService;
import com.team4.services.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.pieces.Piece;
import com.team4.model.Board;
import com.team4.model.pieces.Pawn;
import com.team4.model.pieces.Rook;
import com.team4.model.pieces.King;
import com.team4.model.Space;
import com.team4.repositories.pieces.PieceRepository;

@Service
public class PieceService {
	
	@Autowired
	private PieceRepository pieceRepo;
	
	@Autowired
	private SpaceService spaceService;
	
	@Autowired
	private ChessService chessService;
	
	public PieceService(PieceRepository pieceRepo) {
		this.pieceRepo = pieceRepo;
	}
	
	public Piece createPiece(Piece piece) {
		return pieceRepo.saveAndFlush(piece);
	}
	
	public List<Piece> getAllPieces() {
		return pieceRepo.findAll();
	}
	
	public Piece getPieceById(Long id) {
		return pieceRepo.findById(id).get();
	}
	
	public Piece updatePiece(Piece piece) {
		return pieceRepo.saveAndFlush(piece);
	}
	
	public Chess movePiece(Long id, Integer x, Integer y) {
	    Piece piece = getPieceById(id);
	    
	    Space space = piece.findPossibleMoves().stream()
	            .filter(move -> move.getX() == x && move.getY() == y)
	            .findFirst().orElse(null);

	    if(space != null){
	        space.setPiece(piece);
	        piece.getCurrentSpace().setPiece(null);
	        
	        spaceService.updateSpace(piece.getCurrentSpace());
	        
	        piece.setCurrentSpace(space);
	        
	        switch(piece.getType()) {
		        case PAWN:
		        	Pawn pawn = (Pawn) piece;
		        	pawn.setHasMoved(true);
		        	break;
		        case ROOK:
		        	Rook rook = (Rook) piece;
		        	rook.setHasMoved(true);
		        	break;
		        case KING:
		        	King king = (King) piece;
		        	king.setHasMoved(true);
		        	break;
				default:
					break;
	        }
	        
	        updatePiece(piece);
	        spaceService.updateSpace(space);
	    }

	    return chessService.getChessById(piece.getBoard().getChess().getChessID());
	}
	
	public void deletePieceById(Long id) {
		pieceRepo.deleteById(id);
	}

	public List<Space> getPiecePossibleMoveseById(Long id) {
		Piece piece = pieceRepo.findById(id).get();
		
		return piece.findPossibleMoves();
	}
}