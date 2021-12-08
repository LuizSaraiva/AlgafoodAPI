package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	public Estado salvar(Estado estado) {
		return repository.salvar(estado);
	}
	
	public void deletar(Long idEstado) {
		
		try {
			repository.deletar(idEstado);
		
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Estado com id %d não existe", idEstado));
		}
	}
}
