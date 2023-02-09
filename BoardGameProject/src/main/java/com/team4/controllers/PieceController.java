package com.team4.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.team4.model.classes.Piece;

@RestController
public class PieceController {
  
    @GetMapping("/pieces/{id}")
    public Piece getPieceById(@PathVariable Long id) {
        // This is where you would query your database to retrieve the Piece with the given id
        // For now, we're just returning a dummy piece
        return new Piece(id, "Knight", "White");
    }
}
