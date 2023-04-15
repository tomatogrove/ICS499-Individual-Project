package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Board;
import com.team4.model.Chess;
import com.team4.model.Chess.Status;
import com.team4.repositories.ChessRepository;

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
	
	public Chess getChessById(Long id) {
		return chessRepo.findById(id).orElse(null);
	}
	
	public Chess updateChess(Chess chess) {
		return chessRepo.saveAndFlush(chess);
	}
	
	public void deleteChessById(Long id) {
		chessRepo.deleteById(id);
	}
}
