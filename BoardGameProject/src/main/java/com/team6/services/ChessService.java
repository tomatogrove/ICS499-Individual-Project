package com.team6.services;

import com.team6.model.Board;
import com.team6.model.Chess;
import com.team6.model.Chess.Status;
import com.team6.repositories.ChessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChessService {
	
	@Autowired
	private ChessRepository chessRepo;
	
	public ChessService(ChessRepository chessRepo) {
		this.chessRepo = chessRepo;
	}
	
	public Chess createChess(Chess chess) {
		chess.setStatus(Status.ACTIVE);
		Board board = new Board();
		board.setChess(chess);
		chess.setBoard(board);
		return chessRepo.saveAndFlush(chess);
	}

	public List<Chess> getAllChessGames() {
		return chessRepo.findAll();
	}

	public List<Chess> getAllChessGamesByUserID(Long userID) {
		List<Chess> chessList = chessRepo.findAllByWhitePlayerID(userID).orElse(new ArrayList<>());
		chessList.addAll(chessRepo.findAllByBlackPlayerID(userID).orElse(new ArrayList<>()));

		return chessList;
	}
	
	public Chess getChessById(Long id) {
		return chessRepo.findById(id).orElse(null);
	}

	public Chess forfeitChessMatch(Long chessID, Long userAccountID) {
		Chess chess = getChessById(chessID);
		if (chess != null) {
			Long winnerID = chess.getWhitePlayerID().equals(userAccountID) ? chess.getBlackPlayerID() : chess.getWhitePlayerID();
			chess.setWinnerID(winnerID);
			chess.setStatus(Status.DONE);
			return chessRepo.saveAndFlush(chess);
		}
		return null;
	}
	
	public Chess updateChess(Chess chess) {
		chess.setLastPlayed(LocalDate.now());
		return chessRepo.saveAndFlush(chess);
	}
	
	public void deleteChessById(Long id) {
		chessRepo.deleteById(id);
	}
}
