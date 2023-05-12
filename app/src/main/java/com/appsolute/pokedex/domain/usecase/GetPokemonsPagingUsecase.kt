package com.appsolute.pokedex.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.appsolute.pokedex.domain.paging.PokemonsPagingSource
import com.appsolute.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

class GetPokemonsPagingUsecase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke() = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = {
            PokemonsPagingSource(
                pokemonRepository = pokemonRepository,
                searchQuery = ""
            )
        }
    ).flow
}