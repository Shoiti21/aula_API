package br.com.pokedex.resources;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.exceptions.pokemonNaoListado;
import br.com.pokedex.services.PokemonService;

@RestController
@RequestMapping("/pokedex")
public class PokemonResources {
	@Autowired
	private PokemonService PokemonService;
	
	@RequestMapping(method=RequestMethod.GET) //ALL POKEMONS
	public ResponseEntity<?> lista(){
		return ResponseEntity.status(HttpStatus.OK).body(PokemonService.listar());
	}
	@RequestMapping(method=RequestMethod.POST) //SALVAR
	public ResponseEntity<Void> addPokemon(@RequestBody Pokemon pokemon){
		PokemonService.salvar(pokemon);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pokemon.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET) //ENCONTRAR
	public ResponseEntity<?> findPokemon(@PathVariable Long id){
		Optional<Pokemon> pokemon=null;
		try {
			pokemon=PokemonService.buscar(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(pokemon);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE) //DELETAR
	public ResponseEntity<Void> delPokemon(@PathVariable Long id){
		try {
			PokemonService.deletar(id);
		} catch (pokemonNaoListado e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT) //EDITAR
	public ResponseEntity<Void> editPokemon(@RequestBody Pokemon pokemon, Long id) {
		pokemon.setId(id);
		try {
			PokemonService.atualizar(pokemon);
		} catch (pokemonNaoListado e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
