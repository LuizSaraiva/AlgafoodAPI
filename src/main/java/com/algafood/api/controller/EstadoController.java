package com.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;
import com.algafood.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository repository;

	@Autowired
	private EstadoService service;
	
	@GetMapping
	public List<Estado> todos(){
		return repository.todos();
	}
	
	@GetMapping("/{idEstado}")
	public Estado buscar(@PathVariable Long idEstado) {
		return repository.buscar(idEstado);
	}

	@PostMapping
	public Estado cadastrar(@RequestBody Estado estado) {
		return service.salvar(estado);
	}
	
	@DeleteMapping("/{idEstado}")
	public ResponseEntity<?> delete(@PathVariable Long idEstado) {
		
		try {
			service.deletar(idEstado);
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{idEstado}")
	public ResponseEntity<?> atualizar(
			@PathVariable Long idEstado,
			@RequestBody Estado estado) {
		
		Estado estadoAtual = repository.buscar(idEstado);
		
		if(estadoAtual == null) {
			return ResponseEntity.notFound().build();
		}

			BeanUtils.copyProperties(estado, estadoAtual,"id");
			service.salvar(estadoAtual);
			
			return ResponseEntity.status(HttpStatus.OK).body(estadoAtual);
		
	}
}
