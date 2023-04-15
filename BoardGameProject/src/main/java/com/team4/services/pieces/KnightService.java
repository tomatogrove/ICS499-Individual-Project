package com.team4.services.pieces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.pieces.Knight;
import com.team4.repositories.pieces.KnightRepository;

@Service
public class KnightService {
	
	@Autowired
	private KnightRepository knightRepo;
	
	public KnightService(KnightRepository knightRepo) {
		this.knightRepo = knightRepo;
	}
	
	public Knight createKnight(Knight knight) {
		return knightRepo.saveAndFlush(knight);
	}
	
	public List<Knight> getAllKnights() {
		return knightRepo.findAll();
	}
	
	public Knight getKnightById(Long id) {
		return knightRepo.findById(id).get();
	}
	
	public Knight updateKnight(Knight knight) {
		return knightRepo.saveAndFlush(knight);
	}
	
	public void deleteKnightById(Long id) {
		knightRepo.deleteById(id);
	}
}
