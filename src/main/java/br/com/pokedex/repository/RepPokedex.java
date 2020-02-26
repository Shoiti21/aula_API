package br.com.pokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pokedex.domain.Pokemon;

public interface RepPokedex extends JpaRepository<Pokemon, Long>{

}
