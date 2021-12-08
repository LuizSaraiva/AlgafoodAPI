package com.algafood.domain.service;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	CozinhaRepository repository;
	
	public Cozinha salvar(Cozinha cozinha) {	
		return repository.salvar(cozinha);
	}
	
	public void deletar(Long idCozinha) {
		
		try {
			repository.deletar(idCozinha);			
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cozinha com id %d não encontrada!", idCozinha));
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Não permitido! Cozinha %d esta sendo utilizada em algum restaurante.", idCozinha));
		}
	}
	
}
