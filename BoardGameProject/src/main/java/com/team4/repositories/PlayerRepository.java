package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.classes.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
