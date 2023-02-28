package com.team4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team4.model.Space;
import com.team4.repositories.SpaceRepository;

@RestController
@RequestMapping("/space")
public class SpaceController {

	@Autowired 
	private SpaceRepository SpaceRepo;
	
	@GetMapping("/all")
	public List<Space> list() {
		return SpaceRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Space get(@PathVariable Long id) {
		return SpaceRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Space create(@RequestBody final Space space) {
		return SpaceRepo.saveAndFlush(space);
	}
	
	@PutMapping("/update")
	public Space update(@RequestBody final Space space) {
		return SpaceRepo.saveAndFlush(space);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		SpaceRepo.deleteById(id);
	}
	
}
