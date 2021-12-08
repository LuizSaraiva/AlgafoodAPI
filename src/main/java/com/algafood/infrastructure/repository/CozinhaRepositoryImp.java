package com.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImp implements CozinhaRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cozinha> todos(){
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Override
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Override
	@Transactional
	public void deletar(Long idCozinha) {

		Cozinha cozinha = buscar(idCozinha);

		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cozinha);
	}

	@Override
	public List<Cozinha> buscarPorNome(String nome) {
		return manager.createQuery("from Cozinha where nome = :nome", Cozinha.class)
				.setParameter("nome", nome)
				.getResultList();
	}
}
