package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Knight;
import com.team4.repositories.KnightRepository;

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
		return knightRepo.getReferenceById(id);
	}
	
	public Knight updateKnight(Knight knight) {
		return knightRepo.saveAndFlush(knight);
	}
	
	public void deleteKnightById(Long id) {
		knightRepo.deleteById(id);
	}
}
