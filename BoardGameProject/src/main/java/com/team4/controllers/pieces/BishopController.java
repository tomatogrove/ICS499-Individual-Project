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

import com.team4.model.pieces.Bishop;
import com.team4.services.pieces.BishopService;

@RestController
@RequestMapping("/bishop")
public class BishopController {
	

 	@Autowired 
	private BishopService bishopService;
	
	@GetMapping("/all")
	public List<Bishop> list() {
		return bishopService.getAllBishops();
	}
	
	@GetMapping("/{id}")
	public Bishop get(@PathVariable Long id) {
		return bishopService.getBishopById(id);
	}
	
	@PostMapping("/add")
	public Bishop create(@RequestBody final Bishop bishop) {
		return bishopService.createBishop(bishop);
	}
	
	@PutMapping("/update")
	public Bishop update(@RequestBody final Bishop bishop) {
		return bishopService.updateBishop(bishop);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		bishopService.deleteBishopById(id);
	}

}
