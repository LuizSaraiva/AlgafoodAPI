package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	RestauranteRepository repositoryRestaurante;
	
	@Autowired
	CozinhaRepository repositoryCozinha;
	
	public Restaurante salvar(Restaurante restaurante) {
		
		System.out.println(restaurante.getNome());
		
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = repositoryCozinha.findById(cozinhaId)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Não existe uma cozinha com o id %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return repositoryRestaurante.save(restaurante);
	}
	
	public Restaurante buscaOuFalha(Long idRestaurante) {
		return repositoryRestaurante.findById(idRestaurante).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format("Restaurante com id %d não encontrado!", idRestaurante)));
	}
	
}
