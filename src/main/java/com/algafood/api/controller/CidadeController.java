package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository repository;
	
	@Autowired
	private CidadeService service;
	
	@GetMapping
	public List<Cidade> todos(){
		return repository.findAll();
	}
	
	@GetMapping("/{idCidade}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long idCidade) {
		
		Optional<Cidade> cidade = repository.findById(idCidade);
		
		if(cidade.isPresent()) {
			return ResponseEntity.ok(cidade.get());
		}
		
		return ResponseEntity.notFound().build();
				
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody Cidade cidade) {
		try {
			service.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);	
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
