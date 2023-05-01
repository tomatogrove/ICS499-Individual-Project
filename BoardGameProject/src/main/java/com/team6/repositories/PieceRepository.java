package com.team6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team6.model.pieces.Piece;

public interface PieceRepository extends JpaRepository<Piece, Long> {

}
