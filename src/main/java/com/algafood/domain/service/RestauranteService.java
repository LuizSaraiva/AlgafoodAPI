package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	RestauranteRepository repositoryRestaurante;
	
	@Autowired
	CozinhaService serviceCozinha;
	
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = serviceCozinha.buscarOuFalhar(cozinhaId);
		
		restaurante.setCozinha(cozinha);
		
		return repositoryRestaurante.save(restaurante);
	}
	
	public Restaurante buscaOuFalha(Long idRestaurante) {
		return repositoryRestaurante.findById(idRestaurante).orElseThrow(
				() -> new RestauranteNaoEncontradoException(idRestaurante));
	}
	
}
