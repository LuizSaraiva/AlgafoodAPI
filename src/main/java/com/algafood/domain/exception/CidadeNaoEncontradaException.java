package com.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradaException(Long idCidade) {
		this(
				String.format("Cidade com id %d não encontrada!", idCidade));
	}

}
