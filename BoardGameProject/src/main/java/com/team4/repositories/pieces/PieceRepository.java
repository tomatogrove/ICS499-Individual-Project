package com.team4.repositories.pieces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.pieces.Piece;

public interface PieceRepository extends JpaRepository<Piece , Long>{

}
