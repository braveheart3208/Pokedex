package com.appsolute.pokedex.presentation.event

import com.appsolute.pokedex.application.core.UiText

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

sealed interface UiEvent {
    data class ShowError(val error: UiText) : UiEvent
    object ShowLoading : UiEvent
    data class Navigate(val route : String) : UiEvent
}