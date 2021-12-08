package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	public Estado salvar(Estado estado) {
		return repository.save(estado);
	}
	
	public void deletar(Long idEstado) {
		
		try {
			repository.deleteById(idEstado);			
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Estado com id %d não encontrada!", idEstado));
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Não permitido! Estado %d esta sendo utilizada em alguma cidade.", idEstado));
		}
	}
}
