package com.appsolute.pokedex.presentation.event

import com.appsolute.pokedex.domain.model.PokemonSummary

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

sealed class PokemonListEvent {
    class OnPokemonSelected(val pokemon: PokemonSummary) : PokemonListEvent()

    class OnSearchPokemon(val query: String) : PokemonListEvent()
}
