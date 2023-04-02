package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Piece;
import com.team4.repositories.PieceRepository;

@Service
public class PieceService {
	
	@Autowired
	private PieceRepository pieceRepo;
	
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
	
	public void deletePieceById(Long id) {
		pieceRepo.deleteById(id);
	}
}
