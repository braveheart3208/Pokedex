package com.appsolute.pokedex.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsolute.pokedex.application.core.Resource
import com.appsolute.pokedex.domain.usecase.GetPokemonByNameUsecase
import com.appsolute.pokedex.presentation.event.PokemonDetailEvent
import com.appsolute.pokedex.presentation.event.UiEvent
import com.appsolute.pokedex.presentation.state.PokemonDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
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
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonByNameUsecase: GetPokemonByNameUsecase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PokemonDetailState())
    val state: State<PokemonDetailState> = _state

    private val _uiEventChannel = Channel<UiEvent>()
    val uiEvent = _uiEventChannel.receiveAsFlow()

    init {
        savedStateHandle.get<String>("pokemonName")?.let { pokemonName ->
            getPokemonByName(pokemonName.toLowerCase(Locale("en")))
        }
    }

    fun onEventCalled(event: PokemonDetailEvent) {
        when (event) {
            is PokemonDetailEvent.onBackClicked -> {
                viewModelScope.launch {
                    _uiEventChannel.send(UiEvent.NavigateBack)
                }
            }
        }
    }

    private fun getPokemonByName(name: String) {
        viewModelScope.launch {
            getPokemonByNameUsecase(name)
                .collectLatest { result ->
                    when (result) {
                        is Resource.OnLoading -> {
                            _state.value = state.value.copy(isLoading = true)
                        }

                        is Resource.OnError -> {
                            _state.value =
                                state.value.copy(isLoading = false, error = result.message)
                        }

                        is Resource.OnSuccess -> {
                            _state.value =
                                state.value.copy(isLoading = false, pokemonDetail = result.data)
                        }
                    }
                }
        }
    }
}