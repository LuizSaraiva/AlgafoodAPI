package com.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImp implements RestauranteRepository{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Restaurante> todos() {
		return manager.createQuery("from Restaurante",Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public Restaurante cadastrar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}

	@Override
	@Transactional
	public void deletar(Long id) {

		Restaurante restaurante = buscar(id);

		manager.remove(restaurante);
	}	
}
