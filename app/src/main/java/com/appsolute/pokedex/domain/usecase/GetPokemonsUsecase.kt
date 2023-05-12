package com.appsolute.pokedex.domain.usecase

import com.appsolute.pokedex.application.core.Resource
import com.appsolute.pokedex.application.core.ResultInteractor
import com.appsolute.pokedex.application.core.UiText
import com.appsolute.pokedex.domain.model.PokemonSearchQuery
import com.appsolute.pokedex.domain.model.PokemonSummary
import com.appsolute.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

class GetPokemonsUsecase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ResultInteractor<PokemonSearchQuery, Flow<Resource<List<PokemonSummary>>>>() {
    override val dispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    override suspend fun doWork(params: PokemonSearchQuery): Flow<Resource<List<PokemonSummary>>> =
        flow {
            emit(Resource.OnLoading())
            try {
                val pokemonList = pokemonRepository.getPokemons(params.limit, params.offset)

                emit(Resource.OnSuccess(pokemonList))
            } catch (exception: Exception) {
                emit(Resource.OnError(message = UiText.DynamicString(value = exception.message!!)))
            }
        }


}