package com.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long idRestaurante) {
		this(
				String.format("Restaurante com id %d não encontrado!", idRestaurante));
	}

}
