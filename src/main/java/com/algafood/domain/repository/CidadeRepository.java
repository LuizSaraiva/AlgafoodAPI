package com.algafood.domain.repository;

import java.util.List;

import com.algafood.domain.model.Cidade;

public interface CidadeRepository {

	public List<Cidade> todos();
	public Cidade buscar(Long idCidade);
	public Cidade salvar(Cidade cidade);
	public void deletar(Long idCidade);
}
