package com.appsolute.pokedex.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.appsolute.pokedex.domain.model.PokemonSummary
import com.appsolute.pokedex.domain.repository.PokemonRepository

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

class PokemonsPagingSource(
    private val pokemonRepository: PokemonRepository,
    private val searchQuery : String
) : PagingSource<Int, PokemonSummary>() {

    companion object{
        const val PAGE_SIZE = 10

        private const val INITIAL_LOAD_SIZE = 0;
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonSummary>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonSummary> {
        return try {
            val position = params.key ?: INITIAL_LOAD_SIZE
            val offset = if (params.key != null) ((position - 1) * PAGE_SIZE) else INITIAL_LOAD_SIZE

            val response = pokemonRepository.getPokemons(limit = params.loadSize, offset = offset)

            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + (params.loadSize / PAGE_SIZE)
            }

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}