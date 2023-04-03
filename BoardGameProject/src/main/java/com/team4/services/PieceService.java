package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Piece;
import com.team4.model.Piece.Type;
import com.team4.model.Pawn;
import com.team4.model.Space;
import com.team4.repositories.PieceRepository;

@Service
public class PieceService {
	
	@Autowired
	private PieceRepository pieceRepo;
	
	@Autowired
	private SpaceService spaceService;
	
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
	
	public Piece movePiece(Long id, Integer x, Integer y) {
	    Piece piece = getPieceById(id);
	    Space space = piece.findPossibleMoves().stream()
	            .filter(move -> move.getX() == x && move.getY() == y)
	            .findFirst().orElse(null);

	    if(space != null){
	        space.setPiece(piece);
	        space.setOccupied(true);
	        piece.getCurrentSpace().setPiece(null);
	        piece.getCurrentSpace().setOccupied(false);
	        
	        spaceService.updateSpace(piece.getCurrentSpace());
	        
	        piece.setCurrentSpace(space);
	        if(Type.PAWN.equals(piece.getType())) {
	        	Pawn pawn = (Pawn) piece;
	        	pawn.setHasMoved(true);
	        }
	        updatePiece(piece);
	        
	        spaceService.updateSpace(space);
	    }

	    return piece;
	}
	
	public void deletePieceById(Long id) {
		pieceRepo.deleteById(id);
	}

	public List<Space> getPiecePossibleMoveseById(Long id) {
		Piece piece = pieceRepo.findById(id).get();
		
		return piece.findPossibleMoves();
	}
}
