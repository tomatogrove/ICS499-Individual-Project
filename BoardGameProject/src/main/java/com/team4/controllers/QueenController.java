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

import com.team4.model.classes.chess.pieces.Queen;
import com.team4.repositories.QueenRepository;

@RestController
@RequestMapping("/queen")
public class QueenController {
	
	@Autowired 
	private QueenRepository queenRepo;
	
	@GetMapping("/all")
	public List<Queen> list() {
		return queenRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Queen get(@PathVariable Long id) {
		return queenRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Queen create(@RequestBody final Queen queen) {
		return queenRepo.saveAndFlush(queen);
	}
	
	@PutMapping("/update")
	public Queen update(@RequestBody final Queen queen) {
		return queenRepo.saveAndFlush(queen);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		queenRepo.deleteById(id);
	}

}
