package com.team6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.model.Space;
import com.team6.repositories.SpaceRepository;

@Service
public class SpaceService {
	
	@Autowired
	private SpaceRepository spaceRepo;
	
	public SpaceService(SpaceRepository spaceRepo) {
		this.spaceRepo = spaceRepo;
	}
	
	public Space createSpace(Space space) {
		return spaceRepo.saveAndFlush(space);
	}
	
	public List<Space> getAllSpaces() {
		return spaceRepo.findAll();
	}
	
	public Space getSpaceById(Long id) {
		return spaceRepo.findById(id).get();
	}
	
	public Space updateSpace(Space space) {
		return spaceRepo.saveAndFlush(space);
	}
	
	public void deleteSpaceById(Long id) {
		spaceRepo.deleteById(id);
	}
}
