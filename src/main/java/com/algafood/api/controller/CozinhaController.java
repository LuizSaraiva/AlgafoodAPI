package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;
	
	@Autowired
	private CozinhaService service;
	
	@GetMapping
	public List<Cozinha> cozinhas() {	
		return repository.findAll();
	}
	
	@GetMapping("/{idCozinha}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long idCozinha) {
		
		Optional<Cozinha> cozinha = repository.findById(idCozinha);
		
		if(cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());						
		}
		return ResponseEntity.notFound().build();

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha cadastrar(@RequestBody Cozinha cozinha) {
		return service.salvar(cozinha);
	}
	
	@DeleteMapping("/{idCozinha}")
	public ResponseEntity<?> delete(@PathVariable Long idCozinha) {

		try {
			service.deletar(idCozinha);	
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		
			return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{idCozinha}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long idCozinha,
			@RequestBody Cozinha cozinha){

		Optional<Cozinha> cozinhaAtual = repository.findById(idCozinha);
		
		if(cozinhaAtual.isPresent()) {
		//cozinhaAtual.setNome(cozinha.getNome());
		BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

		Cozinha cozinhaSalva = service.salvar(cozinhaAtual.get());
		return ResponseEntity.ok(cozinhaSalva);
		}
		
		return ResponseEntity.notFound().build();
	}

}