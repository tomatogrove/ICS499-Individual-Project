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

import com.team4.model.Board;
import com.team4.model.Piece;
import com.team4.model.Space;
import com.team4.services.PieceService;

@RestController
@RequestMapping("/piece")
public class PieceController {

	@Autowired 
	private PieceService pieceService;
	
	@GetMapping("/all")
	public List<Piece> list() {
		return pieceService.getAllPieces();
	}
	
	@GetMapping("/{id}")
	public Piece get(@PathVariable Long id) {
		return pieceService.getPieceById(id);
	}
	
	@GetMapping("/possibleMoves/{id}")
	public List<Space> getPossibleMoves(@PathVariable Long id) {
		return pieceService.getPiecePossibleMoveseById(id);
	}	
	
	@PostMapping("/add")
	public Piece create(@RequestBody final Piece piece) {
		return pieceService.createPiece(piece);
	}
	
	@PutMapping("/update")
	public Piece update(@RequestBody final Piece piece) {
		return pieceService.updatePiece(piece);
	}
	
	@PutMapping("/move/{id}/{x}/{y}")
	public Board movePiece(@PathVariable Long id, @PathVariable Integer x, @PathVariable Integer y){
	    return pieceService.movePiece(id, x, y);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		pieceService.deletePieceById(id);
	}
}
