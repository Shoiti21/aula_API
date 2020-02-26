package br.com.pokedex.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.repository.RepPokedex;

@RestController
@RequestMapping("/pokedex")
public class PokemonResources {
	@Autowired
	private RepPokedex Pokedex;
	
	@RequestMapping(method=RequestMethod.GET) //ALL POKEMONS
	public List<Pokemon> showPokemon(){
		return Pokedex.findAll();
	}
	@RequestMapping(method=RequestMethod.POST) //SALVAR
	public void addPokemon(@RequestBody Pokemon pokemon){
		Pokedex.save(pokemon);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET) //ENCONTRAR
	public Optional<Pokemon> findPokemon(@PathVariable Long id){
		return Pokedex.findById(id);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE) //DELETAR
	public void delPokemon(@PathVariable Long id){
		Pokedex.deleteById(id);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT) //EDITAR
	public void editPokemon(@RequestBody Pokemon pokemon, Long id) {
		pokemon.setId(id);
		Pokedex.save(pokemon);
	}
}
