package com.appsolute.pokedex.domain.repository

import com.appsolute.pokedex.domain.model.PokemonDetail
import com.appsolute.pokedex.domain.model.PokemonSummary

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

interface PokemonRepository {
    suspend fun getPokemons(limit: Int, offset: Int): List<PokemonSummary>

    suspend fun getPokemonDetails(pokemonName: String): PokemonDetail?
}