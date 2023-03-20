package com.team4.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Card;
import com.team4.repositories.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepo;

	public CardService(CardRepository cardRepo) {
		this.cardRepo = cardRepo;
	}

	public Card createCard(Card card) {
		return cardRepo.saveAndFlush(card);
	}

	public List<Card> getAllCard() {
		return cardRepo.findAll();
	}

	public Card getCardById(Long id) {
		return cardRepo.getReferenceById(id);
	}

	public Card updateCard(Card card) {
		return cardRepo.saveAndFlush(card);
	}

	public void deleteCardById(Long id) {
		cardRepo.deleteById(id);
	}
}
