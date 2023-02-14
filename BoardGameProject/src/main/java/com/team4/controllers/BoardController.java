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

import com.team4.model.classes.Board;
import com.team4.repositories.BoardRepository;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired 
	private BoardRepository BoardRepo;
	
	@GetMapping("/all")
	public List<Board> list() {
		return BoardRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Board get(@PathVariable Long id) {
		return BoardRepo.getReferenceById(id);
	}
	
	@PostMapping("/add")
	public Board create(@RequestBody final Board board) {
		return BoardRepo.saveAndFlush(board);
	}
	
	@PutMapping("/update")
	public Board update(@RequestBody final Board board) {
		return BoardRepo.saveAndFlush(board);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		BoardRepo.deleteById(id);
	}
	
}
