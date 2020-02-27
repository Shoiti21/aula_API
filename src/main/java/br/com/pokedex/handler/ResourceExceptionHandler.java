package br.com.pokedex.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.pokedex.exceptions.pokemonNaoListado;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(pokemonNaoListado.class)
	public ResponseEntity<Void> handlePokemonNaoListado(pokemonNaoListado e, HttpServletRequest request){
		return ResponseEntity.notFound().build();
	}
}
