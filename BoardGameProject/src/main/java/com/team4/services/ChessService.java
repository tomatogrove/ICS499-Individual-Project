package com.team4.services;

import com.team4.model.Board;
import com.team4.model.Chess;
import com.team4.model.Chess.Status;
import com.team4.repositories.ChessRepository;
import com.team4.services.util.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChessService {
	
	@Autowired
	private ChessRepository chessRepo;

	@Autowired
	private UserAccountService userAccountService;
	
	public ChessService(ChessRepository chessRepo) {
		this.chessRepo = chessRepo;
	}
	
	public Chess createChess(Chess chess) {
		chess.setStatus(Status.ACTIVE);
		Board board = new Board();
		board.setChess(chess);
		chess.setBoard(board);
		Chess savedChess = chessRepo.saveAndFlush(chess);
		userAccountService.updateUser(chess.getWhitePlayer());
		return savedChess;
	}
	
	public List<Chess> getAllChessGames() {
		return chessRepo.findAll();
	}
	
	public Chess getChessById(Long id) {
		return chessRepo.findById(id).orElse(null);
	}
	
	public Chess updateChess(Chess chess) {
		Chess savedChess= chessRepo.saveAndFlush(chess);
		userAccountService.updateUser(chess.getWhitePlayer());
		if (chess.getBlackPlayer() != null) {
			userAccountService.updateUser(chess.getBlackPlayer());
		}
		return savedChess;
	}
	
	public void deleteChessById(Long id) {
		chessRepo.deleteById(id);
	}
}
