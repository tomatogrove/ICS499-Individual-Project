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

import com.team4.model.Pawn;
import com.team4.repositories.PawnRepository;

@RestController
@RequestMapping("/pawn")
public class PawnController {
	
	@Autowired 
	private PawnRepository pawnRepo;
	
	@GetMapping("/all")
	public List<Pawn> list() {
		return pawnRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Pawn get(@PathVariable Long id) {
		return pawnRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Pawn create(@RequestBody final Pawn pawn) {
		return pawnRepo.saveAndFlush(pawn);
	}
	
	@PutMapping("/update")
	public Pawn update(@RequestBody final Pawn pawn) {
		return pawnRepo.saveAndFlush(pawn);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		pawnRepo.deleteById(id);
	}

}
