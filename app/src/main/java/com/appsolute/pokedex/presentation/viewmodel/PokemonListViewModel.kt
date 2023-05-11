package com.appsolute.pokedex.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsolute.pokedex.application.core.Resource
import com.appsolute.pokedex.domain.model.PokemonSearchQuery
import com.appsolute.pokedex.domain.usecase.GetPokemonsUsecase
import com.appsolute.pokedex.presentation.state.PokemonListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUsecase: GetPokemonsUsecase
) : ViewModel() {
    private val _state = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state

    private var getPokemonsJob: Job? = null

    init {
        getPokemons(10, 0)
    }

    private fun getPokemons(limit: Int, offset: Int) {
        getPokemonsJob?.cancel()
        getPokemonsJob = viewModelScope.launch {
            getPokemonsUsecase(
                PokemonSearchQuery(
                    limit = limit, offset = offset
                )
            )
                .collect { result ->
                    when (result) {
                        is Resource.OnLoading -> {
                            _state.value = state.value.copy(isLoading = true)
                        }

                        is Resource.OnError -> {
                            _state.value = state.value.copy(isLoading = false)
                        }

                        is Resource.OnSuccess -> {
                            _state.value =
                                state.value.copy(isLoading = false, pokemons = result.data!!)
                        }
                    }
                }
        }
    }
}