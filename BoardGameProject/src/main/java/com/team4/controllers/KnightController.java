package com.backend.classes;

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


import com.team4.model.classes.Knight;
import com.team4.repositories.KnightRepository;

@RestController
@RequestMapping("/knight")
public class KnightController {
	
	@Autowired 
	private KnightRepository knightRepo;
	
	@GetMapping("/all")
	public List<Knight> list() {
		return knightRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Knight get(@PathVariable Long id) {
		return knightRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Knight create(@RequestBody final Knight knight) {
		return knightRepo.saveAndFlush(knight);
	}
	
	@PutMapping("/update")
	public Knight update(@RequestBody final Knight knight) {
		return knightRepo.saveAndFlush(knight);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		knightRepo.deleteById(id);
	}

}
