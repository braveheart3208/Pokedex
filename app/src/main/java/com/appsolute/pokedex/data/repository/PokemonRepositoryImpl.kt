package com.appsolute.pokedex.data.repository

import com.appsolute.pokedex.data.remote.api.PokemonApi
import com.appsolute.pokedex.domain.model.PokemonDetail
import com.appsolute.pokedex.domain.model.PokemonSummary
import com.appsolute.pokedex.domain.repository.PokemonRepository
import com.appsolute.pokedex.domain.toPokemonSummary
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository {
    override suspend fun getAllPokemons(limit: Int, offset: Int): List<PokemonSummary> {
        return pokemonApi.getPokemonList(limit, offset)
            ?.results
            ?.map {
                it.toPokemonSummary()
            }
            ?: emptyList()
    }

    override suspend fun getPokemonDetails(pokemonName: String): PokemonDetail? {
        return null
    }
}