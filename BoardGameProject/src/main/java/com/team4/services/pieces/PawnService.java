package com.team4.services.pieces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.pieces.Pawn;
import com.team4.repositories.pieces.PawnRepository;

@Service
public class PawnService {
	
	@Autowired
	private PawnRepository pawnRepo;
	
	public PawnService(PawnRepository pawnRepo) {
		this.pawnRepo = pawnRepo;
	}
	
	public Pawn createPawn(Pawn pawn) {
		return pawnRepo.saveAndFlush(pawn);
	}
	
	public List<Pawn> getAllPawns() {
		return pawnRepo.findAll();
	}
	
	public Pawn getPawnById(Long id) {
		return pawnRepo.findById(id).get();
	}
	
	public Pawn updatePawn(Pawn pawn) {
		return pawnRepo.saveAndFlush(pawn);
	}
	
	public void deletePawnById(Long id) {
		pawnRepo.deleteById(id);
	}
}
