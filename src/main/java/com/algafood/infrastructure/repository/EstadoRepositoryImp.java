package com.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImp implements EstadoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> todos() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Override
	public Estado buscar(Long idEstado) {
		return manager.find(Estado.class, idEstado);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	@Transactional
	public void deletar(Long idEstado) {
		
		Estado estado = buscar(idEstado);
		
		if(estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(estado);
	}

}
