package com.appsolute.pokedex.presentation.component.pokemondetail

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.appsolute.pokedex.presentation.event.PokemonDetailEvent
import com.appsolute.pokedex.presentation.state.PokemonDetailState

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

@Composable
fun PokemonDetailScreen(
    pokemonDetailState: PokemonDetailState,
    modifier: Modifier = Modifier,
    onEventCalled: (PokemonDetailEvent) -> Unit
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(dominantColor)
    ) {
        if (pokemonDetailState.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .scale(0.5f)
                    .align(Alignment.Center)
            )
        }

        pokemonDetailState.error?.let {
            Text(
                text = it.asString(),
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        pokemonDetailState.pokemonDetail?.let { pokemonDetail ->
            //Header
            PokemonDetailHeader(
                onEvent = onEventCalled,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .align(Alignment.TopCenter)
            )
            //Content
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(209.dp)) {
                    //Front Image
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(pokemonDetail.frontImageUrl)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        loading = {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .scale(0.5f)
                                    .align(Alignment.Center)
                            )
                        },
                        contentScale = ContentScale.Crop,
                        contentDescription = pokemonDetail.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        onSuccess = {
                            calculateDominantColor(
                                it.result.drawable
                            ) { color ->
                                dominantColor = color
                            }
                        },
                    )
                    //Back Image
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(pokemonDetail.backImageUrl)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        loading = {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .scale(0.5f)
                                    .align(Alignment.Center)
                            )
                        },
                        contentScale = ContentScale.Crop,
                        contentDescription = pokemonDetail.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    )
                }

            }
        }
    }
}

private fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
    val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

    Palette.from(bitmap).generate { palette ->
        palette?.dominantSwatch?.rgb?.let { colorValue ->
            onFinish(Color(colorValue))
        }
    }
}