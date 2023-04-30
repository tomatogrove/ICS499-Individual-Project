package com.team6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.model.Board;
import com.team6.repositories.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepo;
	
	public BoardService(BoardRepository boardRepo) {
		this.boardRepo = boardRepo;
	}
	
	public Board createBoard(Board board) {
		return boardRepo.saveAndFlush(board);
	}
	
	public List<Board> getAllBoards() {
		return boardRepo.findAll();
	}
	
	public Board getBoardById(Long id) {
		return boardRepo.findById(id).orElse(null);
	}
	
	public Board updateBoard(Board board) {
		return boardRepo.saveAndFlush(board);
	}
	
	public void deleteBoardById(Long id) {
		boardRepo.deleteById(id);
	}
}
