package com.algafood.domain.repository;

import java.util.List;

import com.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	public List<Cozinha> todos();
	public Cozinha buscar(Long id);
	public Cozinha salvar(Cozinha cozinha);
	public void deletar(Long idCozinha);
	
	public List<Cozinha> buscarPorNome(String nome);
	
}
