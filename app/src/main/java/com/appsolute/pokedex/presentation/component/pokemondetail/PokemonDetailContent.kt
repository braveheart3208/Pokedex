package com.appsolute.pokedex.presentation.component.pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appsolute.pokedex.application.extension.toUpperCaseFirstLetter
import com.appsolute.pokedex.domain.model.PokemonDetail

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

@Composable
fun PokemonDetailContent(
    pokemonDetail: PokemonDetail,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .offset(y = 190.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Pokemon Name
        Text(
            text = "${pokemonDetail.name.toUpperCaseFirstLetter()} - #${pokemonDetail.id}",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        //Pokemon Type
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            pokemonDetail.types.forEach { type ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 9.dp)
                        .clip(CircleShape)
                        .height(36.dp)
                        .background(getColorByType(type)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = type.toUpperCaseFirstLetter(),
                        color = Color.White,
                        fontSize = 19.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        //Pokemon Weight and Height
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 9.dp)
                    .clip(CircleShape)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Weight: ${pokemonDetail.weight}")
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 9.dp)
                    .clip(CircleShape)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Height: ${pokemonDetail.height}")
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        //
        PokemonStatView(pokemonDetail)
    }
}