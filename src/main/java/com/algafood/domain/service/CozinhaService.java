package com.algafood.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	CozinhaRepository repository;
	
	public Cozinha salvar(Cozinha cozinha) {	
		return repository.save(cozinha);
	}
	
	public void deletar(Long idCozinha) {
		
		try {
			repository.deleteById(idCozinha);			
		}catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, String.format("Cozinha com id %d não encontrada!", idCozinha));
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Não permitido! Cozinha %d esta sendo utilizada em algum restaurante.", idCozinha));
		}
	}
	
}
