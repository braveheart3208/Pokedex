package com.appsolute.pokedex.presentation.component.pokemondetail

import androidx.compose.ui.graphics.Color
import com.appsolute.pokedex.presentation.ui.theme.AtkColor
import com.appsolute.pokedex.presentation.ui.theme.DefColor
import com.appsolute.pokedex.presentation.ui.theme.HPColor
import com.appsolute.pokedex.presentation.ui.theme.SpAtkColor
import com.appsolute.pokedex.presentation.ui.theme.SpDefColor
import com.appsolute.pokedex.presentation.ui.theme.SpdColor
import com.appsolute.pokedex.presentation.ui.theme.TypeBug
import com.appsolute.pokedex.presentation.ui.theme.TypeDark
import com.appsolute.pokedex.presentation.ui.theme.TypeDragon
import com.appsolute.pokedex.presentation.ui.theme.TypeElectric
import com.appsolute.pokedex.presentation.ui.theme.TypeFairy
import com.appsolute.pokedex.presentation.ui.theme.TypeFighting
import com.appsolute.pokedex.presentation.ui.theme.TypeFire
import com.appsolute.pokedex.presentation.ui.theme.TypeFlying
import com.appsolute.pokedex.presentation.ui.theme.TypeGhost
import com.appsolute.pokedex.presentation.ui.theme.TypeGrass
import com.appsolute.pokedex.presentation.ui.theme.TypeGround
import com.appsolute.pokedex.presentation.ui.theme.TypeIce
import com.appsolute.pokedex.presentation.ui.theme.TypeNormal
import com.appsolute.pokedex.presentation.ui.theme.TypePoison
import com.appsolute.pokedex.presentation.ui.theme.TypePsychic
import com.appsolute.pokedex.presentation.ui.theme.TypeRock
import com.appsolute.pokedex.presentation.ui.theme.TypeSteel
import com.appsolute.pokedex.presentation.ui.theme.TypeWater

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

fun getColorByType(pokemonType: String): Color {
    return when (pokemonType) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun getStatColorByName(statName: String): Color {
    return when (statName) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}