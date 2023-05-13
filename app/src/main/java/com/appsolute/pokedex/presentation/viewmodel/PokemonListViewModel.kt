package com.appsolute.pokedex.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.appsolute.pokedex.application.core.Resource
import com.appsolute.pokedex.domain.model.PokemonSearchQuery
import com.appsolute.pokedex.domain.model.PokemonSummary
import com.appsolute.pokedex.domain.usecase.GetPokemonsPagingUsecase
import com.appsolute.pokedex.domain.usecase.GetPokemonsUsecase
import com.appsolute.pokedex.presentation.Screen
import com.appsolute.pokedex.presentation.event.PokemonListEvent
import com.appsolute.pokedex.presentation.event.UiEvent
import com.appsolute.pokedex.presentation.state.PokemonListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
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
    private val getPokemonsUsecase: GetPokemonsUsecase,
    private val getPokemonsPagingUsecase: GetPokemonsPagingUsecase
) : ViewModel() {
    private val _state = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state

    private val _uiEventChannel = Channel<UiEvent>()
    val uiEvent = _uiEventChannel.receiveAsFlow()

    val pagingPokemonItemFlow = getPokemonsPagingUsecase().cachedIn(viewModelScope)

    fun setCurrentPokemonList(pokemonList: List<PokemonSummary>) {
        _state.value = state.value.copy(pokemons = pokemonList)
    }

    private var getPokemonsJob: Job? = null

    init {
//        getPokemons(10, 0)
    }

    fun onEventCalled(event: PokemonListEvent) {
        when (event) {
            is PokemonListEvent.OnPokemonSelected -> {
                val selectedPokemon = event.pokemon
                viewModelScope.launch {
                    _uiEventChannel.send(UiEvent.NavigateForwardTo(Screen.PokeDetailScreen.route + "/${selectedPokemon.name}"))
                }
            }

            is PokemonListEvent.OnSearchPokemon -> {
                val searchQuery = event.query
                if (searchQuery.trim().isEmpty()) {

                }
            }
        }
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