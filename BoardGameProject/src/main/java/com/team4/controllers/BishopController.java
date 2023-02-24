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

import com.team4.model.classes.chess.pieces.Bishop;
import com.team4.repositories.BishopRepository;

@RestController
@RequestMapping("/bishop")
public class BishopController {
	
	@Autowired 
	private BishopRepository BishopRepo;
	
	@GetMapping("/all")
	public List<Bishop> list() {
		return BishopRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Bishop get(@PathVariable Long id) {
		return BishopRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Bishop create(@RequestBody final Bishop bishop) {
		return BishopRepo.saveAndFlush(bishop);
	}
	
	@PutMapping("/update")
	public Bishop update(@RequestBody final Bishop bishop) {
		return BishopRepo.saveAndFlush(bishop);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		BishopRepo.deleteById(id);
	}

}
