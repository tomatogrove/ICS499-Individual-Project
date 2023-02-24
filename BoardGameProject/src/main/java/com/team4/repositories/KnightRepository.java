
package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.classes.chess.pieces.Knight;

public interface KnightRepository extends JpaRepository<Knight, Long> {

}