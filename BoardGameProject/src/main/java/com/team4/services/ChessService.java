package com.team4.services;

import com.team4.model.Board;
import com.team4.model.Chess;
import com.team4.model.Chess.Status;
import com.team4.repositories.ChessRepository;
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
	
	public Chess updateChess(Chess chess) {
		chess.setLastPlayed(LocalDate.now());
		return chessRepo.saveAndFlush(chess);
	}
	
	public void deleteChessById(Long id) {
		chessRepo.deleteById(id);
	}
}
