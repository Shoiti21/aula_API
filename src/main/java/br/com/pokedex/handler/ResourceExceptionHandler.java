package br.com.pokedex.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.pokedex.domain.DetalheErro;
import br.com.pokedex.exceptions.pokemonNaoListado;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(pokemonNaoListado.class)
	public ResponseEntity<DetalheErro> handlePokemonNaoListado(pokemonNaoListado e, HttpServletRequest request){
		DetalheErro erro=new DetalheErro();
		erro.setTitulo("O pokemon não foi encontrado");
		erro.setStatus(404l);
		erro.setMensagem("http://link.com/");
		erro.setTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
