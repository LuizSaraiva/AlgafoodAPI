package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.CidadeRepository;

@Service
public class CidadeService {
	
	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository repositoryCidade;
	
	@Autowired
	private EstadoService serviceEstado;
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = serviceEstado.buscarOuFalhar(estadoId);
				
		cidade.setEstado(estado);
		
		return repositoryCidade.save(cidade);
	}
	
	public void remover(Long idCidade) {
		
		try {
		repositoryCidade.deleteById(idCidade);
		
		}catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(idCidade);
			
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, idCidade));
		}
	}

	public Cidade buscaOuFalha(Long idCidade) {
		return repositoryCidade.findById(idCidade).orElseThrow(
				() -> new CidadeNaoEncontradaException(idCidade));
	}
}
