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

import com.team4.model.pieces.King;
import com.team4.services.pieces.KingService;

@RestController
@RequestMapping("/king")
public class KingController {
	
	@Autowired 
	private KingService kingService;
	
	@GetMapping("/all")
	public List<King> list() {
		return kingService.getAllKings();
	}
	
	@GetMapping("/{id}")
	public King get(@PathVariable Long id) {
		return kingService.getKingById(id);
	}
	
	@PostMapping("/add")
	public King create(@RequestBody final King king) {
		return kingService.createKing(king);
	}
	
	@PutMapping("/update")
	public King update(@RequestBody final King king) {
		return kingService.updateKing(king);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		kingService.deleteKingById(id);
	}
	 
	
}
