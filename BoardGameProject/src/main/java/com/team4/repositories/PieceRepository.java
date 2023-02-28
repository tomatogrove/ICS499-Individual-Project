package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.Piece;

public interface PieceRepository extends JpaRepository<Piece , Long>{

}
