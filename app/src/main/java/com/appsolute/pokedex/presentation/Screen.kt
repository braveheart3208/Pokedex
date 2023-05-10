package com.appsolute.pokedex.presentation

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

sealed class Screen(val route: String) {
    object PokeListScreen : Screen("poke_list_screen")
    object PokeDetailScreen : Screen("poke_list_screen")
}