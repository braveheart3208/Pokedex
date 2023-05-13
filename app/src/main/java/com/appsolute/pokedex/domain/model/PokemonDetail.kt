package com.appsolute.pokedex.domain.model

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val frontImageUrl: String,
    val backImageUrl: String,
    val stats: List<PokemonStat>,
    val types: List<String>,
)