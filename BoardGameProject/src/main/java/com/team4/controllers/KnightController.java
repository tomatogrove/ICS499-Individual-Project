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

import com.team4.model.Knight;
import com.team4.services.KnightService;

@RestController
@RequestMapping("/knight")
public class KnightController {

	@Autowired 
	private KnightService knightService;
	
	@GetMapping("/all")
	public List<Knight> list() {
		return knightService.getAllKnights();
	}
	
	@GetMapping("/{id}")
	public Knight get(@PathVariable Long id) {
		return knightService.getKnightById(id);
	}
	
	@PostMapping("/add")
	public Knight create(@RequestBody final Knight knight) {
		return knightService.createKnight(knight);
	}
	
	@PutMapping("/update")
	public Knight update(@RequestBody final Knight knight) {
		return knightService.updateKnight(knight);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		knightService.deleteKnightById(id);
	}

}
