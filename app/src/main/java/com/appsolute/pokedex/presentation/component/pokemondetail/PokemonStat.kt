package com.appsolute.pokedex.presentation.component.pokemondetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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

    val scrollState = rememberScrollState()

//    LazyColumn(modifier = Modifier
//        .fillMaxSize()
//        .verticalScroll(scrollState)) {
//
//        item {
//            Text(text = "Pokemon Stats:")
//            Spacer(modifier = Modifier.height(6.dp))
//        }
//
//        items(pokemon.stats) { pokemonStat ->
//            StatMeasurementBox(maxBaseStat, pokemonStat)
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .verticalScroll(scrollState)
            .padding(9.dp)
    ) {

        Text(text = "Pokemon Stats:")

        Spacer(modifier = Modifier.height(6.dp))

        pokemon.stats.forEach { pokemonStat ->
            StatMeasurementBox(maxBaseStat, pokemonStat)
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}

@Composable
fun StatMeasurementBox(
    maxBaseStat: Int,
    pokemonStat: PokemonStat
) {

    val indicatorProgress: Float = (pokemonStat.base.toFloat() / maxBaseStat.toFloat())

    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) indicatorProgress else 0f,
        animationSpec = tween(durationMillis = 4000)
    ).value

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(9.dp))
                .padding(top = 1.dp, bottom = 1.dp),
            progress = currentPercentage,
            color = getStatColorByName(pokemonStat.name),
            trackColor = Color.LightGray
        )

        Text(
            text = pokemonStat.name.uppercase(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            textAlign = TextAlign.Start,
        )
    }
}