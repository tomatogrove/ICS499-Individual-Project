package com.team4.controllers;

import com.team4.model.Chess;
import com.team4.services.ChessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chess")
public class ChessController {

	@Autowired 
	private ChessService chessService;
	
	@GetMapping("/all")
	public List<Chess> list() {
		return chessService.getAllChessGames();
	}

	@GetMapping("/all/{id}")
	public List<Chess> getByUserID(@PathVariable Long id) { return chessService.getAllChessGamesByUserID(id); }
	
	@GetMapping("/{id}")
	public Chess get(@PathVariable Long id) {
		return chessService.getChessById(id);
	}
	
	@PostMapping("/add")
	public Chess create(@RequestBody final Chess chess) {
		return chessService.createChess(chess);
	}
	
	@PutMapping("/update")
	public Chess update(@RequestBody final Chess chess) {
		return chessService.updateChess(chess);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		chessService.deleteChessById(id);
	}
	
}
