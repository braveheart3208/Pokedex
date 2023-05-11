package com.appsolute.pokedex.data.remote.api

import com.appsolute.pokedex.data.remote.dto.PokemonDetailDTO
import com.appsolute.pokedex.data.remote.dto.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

interface PokemonApi {
    @GET("/api/v2/pokemon/")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0
    ): PokemonResponse?

    @GET("/api/v2/pokemon/{pokemonName}")
    suspend fun getPokemonDetail(@Path("pokemonName") pokemonName: String): PokemonDetailDTO?
}