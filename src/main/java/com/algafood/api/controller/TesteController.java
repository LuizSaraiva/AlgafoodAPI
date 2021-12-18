package com.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.repository.RestauranteRepository;



@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository repo;
	
	@Autowired
	private RestauranteRepository restauranteRepo;
	
	@GetMapping("cozinhas/por-nome")
	public List<Cozinha> porNome(@RequestParam String nome){
		return repo.findByNome(nome);
	}
	
	@GetMapping("restaurantes/por-nome-e-frete")
	public List<Restaurante> find(
			//@RequestParam(required = true)
			String nome,
			BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal){
		return restauranteRepo.find(nome, taxaFreteInicial, taxaFreteFinal);
		
	}
	
	@GetMapping("restaurantes/com-frete-gratis")
	public List<Restaurante> restauranteComFreteGratis(String nome){
		return restauranteRepo.freteGratis(nome);
	}

}
