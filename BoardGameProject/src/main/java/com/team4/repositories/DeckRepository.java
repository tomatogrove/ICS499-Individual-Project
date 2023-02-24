package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.classes.uno.Deck;

public interface DeckRepository extends JpaRepository<Deck, Long>{

}
