package com.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository 
	extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries{
	
	List<Restaurante> find(
			String nome,
			BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal
			);

}
