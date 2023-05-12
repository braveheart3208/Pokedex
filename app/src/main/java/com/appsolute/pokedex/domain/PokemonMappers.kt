package com.appsolute.pokedex.domain

import com.appsolute.pokedex.application.extension.toUpperCaseFirstLetter
import com.appsolute.pokedex.data.remote.dto.PokemonDetailDTO
import com.appsolute.pokedex.data.remote.dto.PokemonSummaryDTO
import com.appsolute.pokedex.domain.model.PokemonDetail
import com.appsolute.pokedex.domain.model.PokemonStat
import com.appsolute.pokedex.domain.model.PokemonSummary

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

fun PokemonSummaryDTO.toPokemonSummary(): PokemonSummary {
    //Presume format will end with .../index/
    /*
    Remove the very last "/" and only takes number from the result string
     */
    val pokemonIndex = if (this.url.endsWith("/")) {
        this.url.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        this.url.takeLastWhile { it.isDigit() }
    }
    return PokemonSummary(
        name = this.name.toUpperCaseFirstLetter(),
        imageUrl = "https://unpkg.com/pokeapi-sprites@2.0.4/sprites/pokemon/other/dream-world/$pokemonIndex.svg"
    )
}

fun PokemonDetailDTO.toPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        frontImageUrl = this.sprites.front_default,
        backImageUrl = this.sprites.back_default,
        stats = this.stats.map {
            PokemonStat(
                name = it.stat.name,
                base = it.base_stat
            )
        },
        types = this.types.map {
            it.type.name
        }
    )
}