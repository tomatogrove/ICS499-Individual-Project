package com.team4.repositories;

import com.team4.model.Chess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChessRepository extends JpaRepository<Chess, Long> {
    Optional<List<Chess>> findAllByWhitePlayerID(Long whitePlayerID);

    Optional<List<Chess>> findAllByBlackPlayerID(Long blackPlayerID);
}
