package com.team4.services.pieces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.pieces.King;
import com.team4.repositories.pieces.KingRepository;

@Service
public class KingService {
	
	@Autowired
	private KingRepository kingRepo;
	
	public KingService(KingRepository kingRepo) {
		this.kingRepo = kingRepo;
	}
	
	public King createKing(King king) {
		return kingRepo.saveAndFlush(king);
	}
	
	public List<King> getAllKings() {
		return kingRepo.findAll();
	}
	
	public King getKingById(Long id) {
		return kingRepo.findById(id).get();
	}
	
	public King updateKing(King king) {
		return kingRepo.saveAndFlush(king);
	}
	
	public void deleteKingById(Long id) {
		kingRepo.deleteById(id);
	}
}