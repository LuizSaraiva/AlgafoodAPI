package com.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
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
	public Restaurante buscar(@PathVariable Long idRestaurante) {
		return service.buscaOuFalha(idRestaurante);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante cadastrar(@RequestBody Restaurante restaurante) {
	
		System.out.println(restaurante.getCozinha());
		
		try {	
			return service.salvar(restaurante);
		}catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{idRestaurante}")
	public Restaurante atualizar(
			@PathVariable Long idRestaurante,
			@RequestBody Restaurante restaurante){
	
			Restaurante restauranteAtual = service.buscaOuFalha(idRestaurante);
				
			BeanUtils.copyProperties(restaurante, restauranteAtual, 
				"id", "formaPagamento","endereco","dataCadastro","produtos");
	
		try {		
				return service.salvar(restauranteAtual);
		}catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PatchMapping("/{idRestaurante}")
	public Restaurante atualizarParcial(
			@PathVariable Long idRestaurante,
			@RequestBody Map<String, Object> campos
			){
		
		Restaurante restauranteAtual = service.buscaOuFalha(idRestaurante);
				
		merge(campos, restauranteAtual);	
		
		return atualizar(idRestaurante, restauranteAtual);
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
