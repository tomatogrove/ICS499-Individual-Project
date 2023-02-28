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

import com.team4.model.Rook;
import com.team4.repositories.RookRepository;

@RestController
@RequestMapping("/rook")
public class RookController {
	
	@Autowired 
	private RookRepository RookRepo;
	
	@GetMapping("/all")
	public List<Rook> list() {
		return RookRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Rook get(@PathVariable Long id) {
		return RookRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Rook create(@RequestBody final Rook rook) {
		return RookRepo.saveAndFlush(rook);
	}
	
	@PutMapping("/update")
	public Rook update(@RequestBody final Rook rook) {
		return RookRepo.saveAndFlush(rook);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		RookRepo.deleteById(id);
	}

}
