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
	public Estado buscar(@PathVariable Long idEstado) {
		return service.buscarOuFalhar(idEstado);		
	}

	@PostMapping
	public Estado cadastrar(@RequestBody Estado estado) {
		return service.salvar(estado);
	}
	
	@DeleteMapping("/{idEstado}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idEstado) {
			service.deletar(idEstado);
	}
	
	@PutMapping("/{idEstado}")
	public Estado atualizar(
			@PathVariable Long idEstado,
			@RequestBody Estado estado) {
		
			Estado estadoAtual = service.buscarOuFalhar(idEstado);
		
			BeanUtils.copyProperties(estado, estadoAtual,"id");
			return service.salvar(estadoAtual);			
	}
}
