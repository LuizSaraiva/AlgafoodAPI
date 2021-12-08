package com.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algafood.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{
	
	//public List<Cozinha> buscarPorNome(String nome);
	
}
