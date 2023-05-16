package com.appsolute.pokedex.presentation.component.pokemondetail

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.appsolute.pokedex.domain.model.PokemonDetail
import com.appsolute.pokedex.domain.model.PokemonStat

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

@Composable
fun PokemonStatView(
    pokemon: PokemonDetail
) {
    val maxBaseStat = remember {
        pokemon.stats.maxOf { it.base }
    }

    Column(modifier = Modifier
        .fillMaxWidth()) {
        Text(text = "Pokemon Stats:")

        Spacer(modifier = Modifier.height(6.dp))

        pokemon.stats.forEach { pokemonStat ->
            StatMeasurementBox(maxBaseStat, pokemonStat)
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun StatMeasurementBox(
    maxBaseStat: Int,
    pokemonStat: PokemonStat
) {

    val indicatorProgress = (pokemonStat.stat / maxBaseStat).toFloat()

    var progress by remember { mutableStateOf(0f) }
    val progressAnimDuration = 1500
    val progressAnimation by animateFloatAsState(
        targetValue = indicatorProgress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing)
    )

    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(CircleShape)
            .padding(9.dp),
        progress = progressAnimation,
    )

    LaunchedEffect(indicatorProgress) {
        progress = indicatorProgress
    }
}