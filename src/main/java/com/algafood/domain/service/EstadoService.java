package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	private static final String MSG_ESTADO_EM_USO = "Não permitido! Estado %d esta sendo utilizada em alguma cidade.";

	@Autowired
	private EstadoRepository repository;
	
	public Estado salvar(Estado estado) {
		return repository.save(estado);
	}
	
	public void deletar(Long idEstado) {
		
		try {
			repository.deleteById(idEstado);			
		}catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(idEstado);
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, idEstado));
		}
	}
	
	public Estado buscarOuFalhar(Long idEstado) {
		return repository.findById(idEstado).orElseThrow(
				() -> new EstadoNaoEncontradoException(idEstado));
	}
}
