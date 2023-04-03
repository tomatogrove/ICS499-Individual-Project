package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Rook;
import com.team4.repositories.RookRepository;

@Service
public class RookService {
	
	@Autowired
	private RookRepository rookRepo;
	
	public RookService(RookRepository rookRepo) {
		this.rookRepo = rookRepo;
	}
	
	public Rook createRook(Rook rook) {
		return rookRepo.saveAndFlush(rook);
	}
	
	public List<Rook> getAllRooks() {
		return rookRepo.findAll();
	}
	
	public Rook getRookById(Long id) {
		return rookRepo.findById(id).get();
	}
	
	public Rook updateRook(Rook rook) {
		return rookRepo.saveAndFlush(rook);
	}
	
	public void deleteRookById(Long id) {
		rookRepo.deleteById(id);
	}
}
