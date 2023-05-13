package com.appsolute.pokedex.domain.usecase

import com.appsolute.pokedex.application.core.Resource
import com.appsolute.pokedex.application.core.ResultInteractor
import com.appsolute.pokedex.application.core.UiText
import com.appsolute.pokedex.domain.model.PokemonDetail
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

class GetPokemonByNameUsecase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ResultInteractor<String, Flow<Resource<PokemonDetail>>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun doWork(params: String): Flow<Resource<PokemonDetail>> = flow {
        try {
            emit(Resource.OnLoading())

            val response = pokemonRepository.getPokemonDetails(params)

            response?.let {
                emit(Resource.OnSuccess(data = response))
            }?: emit(Resource.OnError(message = UiText.DynamicString("Pokemon Not Found")))


        }catch (exception : Exception){
            emit(Resource.OnError(message = UiText.DynamicString(exception.localizedMessage!!)))
        }
    }
}