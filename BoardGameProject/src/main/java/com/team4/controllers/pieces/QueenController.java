package com.team4.controllers.pieces;

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

import com.team4.model.pieces.Queen;
import com.team4.services.pieces.QueenService;

@RestController
@RequestMapping("/queen")
public class QueenController {
	
	@Autowired 
	private QueenService queenService;
	
	@GetMapping("/all")
	public List<Queen> list() {
		return queenService.getAllQueens();
	}
	
	@GetMapping("/{id}")
	public Queen get(@PathVariable Long id) {
		return queenService.getQueenById(id);
	}
	
	@PostMapping("/add")
	public Queen create(@RequestBody final Queen queen) {
		return queenService.createQueen(queen);
	}
	
	@PutMapping("/update")
	public Queen update(@RequestBody final Queen queen) {
		return queenService.updateQueen(queen);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		queenService.deleteQueenById(id);
	}

}
