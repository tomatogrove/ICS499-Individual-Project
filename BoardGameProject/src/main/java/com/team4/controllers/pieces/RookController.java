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

import com.team4.model.pieces.Rook;
import com.team4.services.pieces.RookService;

@RestController
@RequestMapping("/rook")
public class RookController {
	

	@Autowired 
	private RookService rookService;
	
	@GetMapping("/all")
	public List<Rook> list() {
		return rookService.getAllRooks();
	}
	
	@GetMapping("/{id}")
	public Rook get(@PathVariable Long id) {
		return rookService.getRookById(id);
	}
	
	@PostMapping("/add")
	public Rook create(@RequestBody final Rook rook) {
		return rookService.createRook(rook);
	}
	
	@PutMapping("/update")
	public Rook update(@RequestBody final Rook rook) {
		return rookService.updateRook(rook);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		rookService.deleteRookById(id);
	}

}
