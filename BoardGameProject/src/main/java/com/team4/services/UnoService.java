package com.team4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.model.Uno;
import com.team4.repositories.UnoRepository;

@Service
public class UnoService {

	@Autowired
	private UnoRepository unoRepo;

	public UnoService(UnoRepository unoRepo) {
		this.unoRepo = unoRepo;
	}

	public Uno createUno(Uno uno) {
		return unoRepo.saveAndFlush(uno);
	}

	public List<Uno> getAllUno() {
		return unoRepo.findAll();
	}

	public Uno getUnoById(Long id) {
		return unoRepo.getReferenceById(id);
	}

	public Uno updateUno(Uno uno) {
		return unoRepo.saveAndFlush(uno);
	}

	public void deleteUnoById(Long id) {
		unoRepo.deleteById(id);
	}
}
