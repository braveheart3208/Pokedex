package com.appsolute.pokedex.presentation.state

import com.appsolute.pokedex.application.core.UiText
import com.appsolute.pokedex.domain.model.PokemonSummary

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemons: List<PokemonSummary> = emptyList(),
    val error: UiText? = null
)
