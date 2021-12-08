package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.algafood.domain.exception.EntidadeEmUsoException;
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
		return repository.findAll();
	}
	
	@GetMapping("/{idEstado}")
	public ResponseEntity<Estado> buscar(@PathVariable Long idEstado) {
		
		Optional<Estado> estado = repository.findById(idEstado);
		
		if(estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}
		return ResponseEntity.notFound().build();
		
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
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{idEstado}")
	public ResponseEntity<?> atualizar(
			@PathVariable Long idEstado,
			@RequestBody Estado estado) {
		
		Optional<Estado> estadoAtual = repository.findById(idEstado);
		
		if(estadoAtual.isPresent()) {

			BeanUtils.copyProperties(estado, estadoAtual.get(),"id");
			Estado estadoSalvar = service.salvar(estadoAtual.get());
			
			return ResponseEntity.ok(estadoSalvar);
		}
			return ResponseEntity.notFound().build();
		
	}
}
