package com.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImp implements CidadeRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> todos() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long idCidade) {
		return manager.find(Cidade.class, idCidade);
	}

	@Override
	@Transactional
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Override
	@Transactional
	public void deletar(Long idCidade) {
		
		Cidade cidade = buscar(idCidade);
		
		if(cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cidade);
	}

}
