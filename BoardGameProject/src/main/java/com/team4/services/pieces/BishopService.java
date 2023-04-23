package com.team4.services.pieces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.pieces.Bishop;
import com.team4.repositories.pieces.BishopRepository;

@Service
public class BishopService {
	
	@Autowired
	private BishopRepository bishopRepo;
	
	public BishopService(BishopRepository bishopRepo) {
		this.bishopRepo = bishopRepo;
	}
	
	public Bishop createBishop(Bishop bishop) {
		return bishopRepo.saveAndFlush(bishop);
	}
	
	public List<Bishop> getAllBishops() {
		return bishopRepo.findAll();
	}
	
	public Bishop getBishopById(Long id) {
		return bishopRepo.findById(id).get();
	}
	
	public Bishop updateBishop(Bishop bishop) {
		return bishopRepo.saveAndFlush(bishop);
	}
	
	public void deleteBishopById(Long id) {
		bishopRepo.deleteById(id);
	}
}
