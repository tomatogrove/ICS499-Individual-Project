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

import com.team4.model.Uno;
import com.team4.repositories.UnoRepository;

@RestController
@RequestMapping("/uno")
public class UnoController {
	
	@Autowired 
	private UnoRepository unoRepo;
	
	@GetMapping("/all")
	public List<Uno> list() {
		return unoRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Uno get(@PathVariable Long id) {
		return unoRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Uno create(@RequestBody final Uno uno) {
		return unoRepo.saveAndFlush(uno);
	}
	
	@PutMapping("/update")
	public Uno update(@RequestBody final Uno uno) {
		return unoRepo.saveAndFlush(uno);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		unoRepo.deleteById(id);
	}

}
