package com.algafood.domain.repository;

import java.util.List;

import com.algafood.domain.model.Estado;

public interface EstadoRepository {

	public List<Estado> todos();
	public Estado buscar(Long idEstado);
	public Estado salvar(Estado estado);
	public void deletar(Long idEstado);
}
