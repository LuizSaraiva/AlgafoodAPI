package com.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository 
	extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{
	
	List<Restaurante> find(
			String nome,
			BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal
			);
	
	@Query("from Restaurante r join r.cozinha")
	List<Restaurante> findAll();

}
