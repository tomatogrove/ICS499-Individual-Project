package com.team4.services.pieces;

import com.team4.model.Chess;
import com.team4.model.Space;
import com.team4.model.pieces.King;
import com.team4.model.pieces.Pawn;
import com.team4.model.pieces.Piece;
import com.team4.model.pieces.Rook;
import com.team4.repositories.pieces.PieceRepository;
import com.team4.services.ChessService;
import com.team4.services.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
		return pieceRepo.findById(id).orElse(null);
	}

	public Piece updatePiece(Piece piece) {
		return pieceRepo.saveAndFlush(piece);
	}

	public Chess movePiece(Long id, Integer x, Integer y) {
		Piece piece = getPieceById(id);
		if (piece.getBoard().getChess().getStatus().equals(Chess.Status.ACTIVE)) {

			Space space = piece.findPossibleMoves().stream()
					.filter(move -> move.getX() == x && move.getY() == y)
					.findFirst().orElse(null);

			if (space != null) {
				if (space.isOccupied() && space.getPiece().getType().equals(Piece.Type.KING)) {
					Chess chess = space.getBoard().getChess();
					chess.setStatus(Chess.Status.DONE);
					chess.setWinnerByColor(piece.getColor());
					chessService.updateChess(chess);
				}
				space.setPiece(piece);
				piece.getCurrentSpace().setPiece(null);

				spaceService.updateSpace(piece.getCurrentSpace());

				piece.setCurrentSpace(space);

				switch (piece.getType()) {
					case PAWN -> {
						Pawn pawn = (Pawn) piece;
						pawn.setHasMoved(true);
					}
					case ROOK -> {
						Rook rook = (Rook) piece;
						rook.setHasMoved(true);
					}
					case KING -> {
						King king = (King) piece;
						king.setHasMoved(true);
					}
					default -> {
					}
				}

				updatePiece(piece);
				spaceService.updateSpace(space);
			}
		}

		return chessService.getChessById(piece.getBoard().getChess().getChessID());
	}

	public void deletePieceById(Long id) {
		pieceRepo.deleteById(id);
	}

	public List<Space> getPiecePossibleMovesById(Long id) {
		Piece piece = pieceRepo.findById(id).orElse(null);

		if (piece != null && piece.getBoard().getChess().getStatus().equals(Chess.Status.ACTIVE)) {
			return piece.findPossibleMoves();
		}
		return null;
	}
}
