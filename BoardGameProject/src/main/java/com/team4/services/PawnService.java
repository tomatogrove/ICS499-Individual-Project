package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Pawn;
import com.team4.repositories.PawnRepository;

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
		return pawnRepo.getReferenceById(id);
	}
	
	public Pawn updatePawn(Pawn pawn) {
		return pawnRepo.saveAndFlush(pawn);
	}
	
	public void deletePawnById(Long id) {
		pawnRepo.deleteById(id);
	}
}
