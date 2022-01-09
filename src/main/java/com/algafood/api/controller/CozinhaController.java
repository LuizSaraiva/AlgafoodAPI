package com.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public Cozinha buscar(@PathVariable Long idCozinha) {
		return service.buscarOuFalhar(idCozinha);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha cadastrar(@RequestBody Cozinha cozinha) {
		return service.salvar(cozinha);
	}
	
	@DeleteMapping("/{idCozinha}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idCozinha) {
		service.deletar(idCozinha);
	}
	
	
	@PutMapping("/{idCozinha}")
	public Cozinha atualizar(@PathVariable Long idCozinha,
			@RequestBody Cozinha cozinha){

		Cozinha cozinhaAtual = service.buscarOuFalhar(idCozinha);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

		return service.salvar(cozinhaAtual);
	}
		
}