package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Não permitido! Cozinha %d esta sendo utilizada em algum restaurante.";

	@Autowired
	CozinhaRepository repository;

	
	public Cozinha salvar(Cozinha cozinha) {	
		return repository.save(cozinha);
	}
	
	public void deletar(Long idCozinha) {
		
		try {
			repository.deleteById(idCozinha);			
		}catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(idCozinha);
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, idCozinha));
		}
	}
	
	public Cozinha buscarOuFalhar(Long idCozinha) {
		return repository.findById(idCozinha).orElseThrow(() -> 
				new CozinhaNaoEncontradaException(idCozinha));
	}
	
}
