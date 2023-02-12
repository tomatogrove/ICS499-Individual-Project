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

import com.team4.model.classes.King;
import com.team4.repositories.KingRepository;

@RestController
@RequestMapping("/king")
public class KingController {
	
	@Autowired 
	private KingRepository kingRepo;
	
	@GetMapping("/all")
	public List<King> list() {
		return kingRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public King get(@PathVariable Long id) {
		return kingRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public King create(@RequestBody final King king) {
		return kingRepo.saveAndFlush(king);
	}
	
	@PutMapping("/update")
	public King update(@RequestBody final King king) {
		return kingRepo.saveAndFlush(king);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		kingRepo.deleteById(id);
	}

}
