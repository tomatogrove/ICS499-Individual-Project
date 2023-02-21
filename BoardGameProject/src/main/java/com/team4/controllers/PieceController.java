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

import com.team4.model.abstrct.Piece;
import com.team4.repositories.PieceRepository;

@RestController
@RequestMapping("/piece")
public class PieceController {
	@Autowired
	private PieceRepository pieceRepo;

	@GetMapping("/all")
	public List<Piece> list() {
		return pieceRepo.findAll();
	}

	@GetMapping("/{id}")
	public Piece get(@PathVariable Long id) {
		return pieceRepo.getReferenceById(id);
	}

	@PostMapping("/add")
	public Piece create(@RequestBody final Piece piece) {
		return pieceRepo.saveAndFlush(piece);
	}

	@PutMapping("/update")
	public Piece update(@RequestBody final Piece piece) {
		return pieceRepo.saveAndFlush(piece);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		pieceRepo.deleteById(id);
	}
}
