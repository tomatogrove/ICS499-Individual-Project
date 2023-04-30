package com.team6.services;

import com.team6.model.Board;
import com.team6.model.Chess;
import com.team6.model.Space;
import com.team6.model.enums.Status;
import com.team6.model.enums.Type;
import com.team6.model.pieces.King;
import com.team6.model.pieces.Pawn;
import com.team6.model.pieces.Piece;
import com.team6.model.pieces.Rook;
import com.team6.model.enums.Color;
import com.team6.repositories.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.team6.model.Chess.*;
import static com.team6.model.pieces.Piece.*;

@Service
public class PieceService {

	@Autowired
	private PieceRepository pieceRepo;

	@Autowired
	private SpaceService spaceService;

	@Autowired
	private ChessService chessService;
	@Autowired
	private BoardService boardService;

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
		Board board = piece.getBoard();

		if (board.getChess().getStatus().equals(Status.ACTIVE)) {
			Space space = piece.findPossibleMoves().stream()
					.filter(move -> move.getX() == x && move.getY() == y)
					.findFirst().orElse(null);

			if (space != null) {
				Chess chess = space.getBoard().getChess();
				if (space.isOccupied() && space.getPiece().getType().equals(Type.KING)) {
					chess.setStatus(Status.DONE);
					chess.setWinnerByColor(piece.getColor());
				} else {
					chess.setCurrentTurn(chess.getCurrentTurn().equals(Color.WHITE) ? Color.BLACK : Color.WHITE);
				}
				chessService.updateChess(chess);

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

		if (piece != null && piece.getBoard().getChess().getStatus().equals(Status.ACTIVE)) {
			List<Space> spaceList = piece.findPossibleMoves();

			if(piece.getType().equals(Type.KING)){
				Board boardCopy = boardService.getBoardById(piece.getBoard().getBoardID());

				List<Space> invalidMoves = new ArrayList<>();

				for (Space space : spaceList) {
					boardCopy.getSpaces().stream()
							.filter(s -> s.getSpaceID() == space.getSpaceID())
							.findFirst().ifPresent(spaceCopy -> {
								spaceCopy.setPiece(piece);
								if(((King) piece).inCheck(spaceCopy, boardCopy)){
									invalidMoves.add(space);
								}
							});
				}

				spaceList.removeAll(invalidMoves);
			}

			return spaceList;
		}
		return null;
	}
}
