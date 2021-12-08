package com.algafood.api.controller;

import java.util.List;

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
import com.algafood.infrastructure.repository.CozinhaRepositoryImp;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;
	
	@Autowired
	private CozinhaService service;
	
	@GetMapping
	public List<Cozinha> cozinhas() {	
		return repository.todos();
	}
	
	@GetMapping("/{idCozinha}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long idCozinha) {
		
		Cozinha cozinha = repository.buscar(idCozinha);
		
		if(cozinha == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cozinha);
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

		Cozinha cozinhaAtual = repository.buscar(idCozinha);
		
		if(cozinhaAtual != null) {
		//cozinhaAtual.setNome(cozinha.getNome());
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

		cozinhaAtual = service.salvar(cozinhaAtual);
		return ResponseEntity.ok(cozinhaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}

}