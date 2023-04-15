package com.team4.services.pieces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.pieces.Queen;
import com.team4.repositories.pieces.QueenRepository;

@Service
public class QueenService {
	
	@Autowired
	private QueenRepository queenRepo;
	
	public QueenService(QueenRepository queenRepo) {
		this.queenRepo = queenRepo;
	}
	
	public Queen createQueen(Queen queen) {
		return queenRepo.saveAndFlush(queen);
	}
	
	public List<Queen> getAllQueens() {
		return queenRepo.findAll();
	}
	
	public Queen getQueenById(Long id) {
		return queenRepo.findById(id).get();
	}
	
	public Queen updateQueen(Queen queen) {
		return queenRepo.saveAndFlush(queen);
	}
	
	public void deleteQueenById(Long id) {
		queenRepo.deleteById(id);
	}
}
