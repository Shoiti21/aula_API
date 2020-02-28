package br.com.pokedex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.exceptions.pokemonNaoListado;
import br.com.pokedex.repository.RepPokedex;

@Service
public class PokemonService {
	@Autowired
	private RepPokedex Pokedex;
	
	public List<Pokemon> listar(){
		return Pokedex.findAll();
	}
	public Optional<Pokemon> buscar(Long id) {
		Optional<Pokemon> pokemon=Pokedex.findById(id);
		if (pokemon.isEmpty()) {
			throw new pokemonNaoListado("Esse item não existe!");
		}
		return pokemon;
	}
	public void salvar(Pokemon pokemon) {
		pokemon.setId(null);
		Pokedex.save(pokemon);
	}
	public void deletar(Long id) {
		try {
			Pokedex.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new pokemonNaoListado("Esse item não existe!");
		}
	}
	public void atualizar(Pokemon pokemon, Long id) {
		pokemon.setId(id);
		Pokedex.save(pokemon);
	}
}
