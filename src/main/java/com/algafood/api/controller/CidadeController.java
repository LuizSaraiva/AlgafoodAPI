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

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.exception.NegocioException;
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
	public Cidade buscar(@PathVariable Long idCidade) {
		return service.buscaOuFalha(idCidade);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade cadastrar(@RequestBody Cidade cidade) {
		try {
			return service.salvar(cidade);
		}catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}	
	}
	
	@DeleteMapping("/{idCidade}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(Long idCidade) {
		service.remover(idCidade);
	}
	
	@PutMapping("/{idCidade}")
	public Cidade atualizar(
			@PathVariable Long idCidade,
			@RequestBody Cidade cidade) {
		
		Cidade cidadeAtual = service.buscaOuFalha(idCidade);
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		try {
			return service.salvar(cidadeAtual);
		}catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
}
