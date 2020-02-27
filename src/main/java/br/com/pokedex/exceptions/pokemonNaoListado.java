package br.com.pokedex.exceptions;

public class pokemonNaoListado extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4257696841032100787L;
	public pokemonNaoListado(String mensagem) {
		super(mensagem);
	}
	public pokemonNaoListado(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
