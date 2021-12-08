package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.repository.EstadoRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repositoryCidade;
	
	@Autowired
	private EstadoRepository repositoryEstado;
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = repositoryEstado.buscar(estadoId);
		
		if(estado == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Estado não existe ou nulo!", estadoId));
		}
		
		cidade.setEstado(estado);
		
		return repositoryCidade.save(cidade);
	}
	
	public void remover(Long idCidade) {
		
		try {
		repositoryCidade.deleteById(idCidade);
		
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Cidade com id %d não encontrada!", idCidade));
		}
	}

}
