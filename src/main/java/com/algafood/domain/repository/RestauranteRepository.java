package com.algafood.domain.repository;

import java.util.List;

import com.algafood.domain.model.Restaurante;

public interface RestauranteRepository{

	List<Restaurante> todos();
	Restaurante buscar(Long id);
	Restaurante cadastrar(Restaurante restaurante);
	void deletar(Long id);
}
