package com.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private RestauranteService service;
	
	@GetMapping
	public List<Restaurante> todos(){
		return repository.findAll();
	}
	
	@GetMapping("/{idRestaurante}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long idRestaurante) {
		
		Optional<Restaurante> restaurante = repository.findById(idRestaurante);
		
		if(restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> cadastrar(@RequestBody Restaurante restaurante) {
		
		try {
			restaurante = service.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
				
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/{idRestaurante}")
	public ResponseEntity<?> atualizar(
			@PathVariable Long idRestaurante,
			@RequestBody Restaurante restaurante){
		try {
		Optional<Restaurante> restauranteAtual = repository.findById(idRestaurante);
				
			if(restauranteAtual.isPresent()) {
				
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
				
				Restaurante restauranteSalvar = service.salvar(restauranteAtual.get());
				return ResponseEntity.ok(restauranteSalvar);					
				
			}
			return ResponseEntity.notFound().build();
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{idRestaurante}")
	public ResponseEntity<?> atualizarParcial(
			@PathVariable Long idRestaurante,
			@RequestBody Map<String, Object> campos
			){
		
		Optional<Restaurante> restauranteAtual = repository.findById(idRestaurante);
		
		if(restauranteAtual == null) {
			return ResponseEntity.notFound().build();	
		}
		
		merge(campos, restauranteAtual.get());	
		
		return atualizar(idRestaurante, restauranteAtual.get());
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino ) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
		
		
		camposOrigem.forEach((nomePropriedade, valorPropriedade)->{
			 Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			 field.setAccessible(true);
			 
			 Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			 
			 ReflectionUtils.setField(field, restauranteDestino, novoValor);
		}); 
	}
}
