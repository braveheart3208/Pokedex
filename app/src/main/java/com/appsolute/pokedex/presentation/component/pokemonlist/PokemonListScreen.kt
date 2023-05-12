package com.appsolute.pokedex.presentation.component.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.appsolute.pokedex.R
import com.appsolute.pokedex.domain.model.PokemonSummary
import com.appsolute.pokedex.presentation.event.PokemonListEvent
import com.appsolute.pokedex.presentation.state.PokemonListState

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

@Composable
fun PokemonListScreen(
    lazyPagingItems: LazyPagingItems<PokemonSummary>,
    state: PokemonListState,
    onEventCalled: (PokemonListEvent) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            @Composable
            fun getSpacer() = Spacer(modifier = Modifier.height(19.dp))

            getSpacer()
            Image(
                painter = painterResource(id = R.drawable.logo_pokemon),
                contentDescription = "Pokemon Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
                    .padding(6.dp)
            )
            getSpacer()
            SearchBar(
                onSearch = { onEventCalled(PokemonListEvent.OnSearchPokemon(it)) },
                hint = "Find your pokemon...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            getSpacer()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(9.dp),
                verticalArrangement = Arrangement.spacedBy(9.dp),
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                //This part only has default Grid
//                items(state.pokemons) {
//                    PokemondexEntry(
//                        entry = it,
//                        navController = navController,
//                        modifier = Modifier.clickable {
////                navController.navigate(Screen.PokeDetailScreen.route + "/${dominantColor.toArgb()}/${entry.name}")
//                        }
//                    )
//                }
                //This one has pagination - Load more feature...
                items(lazyPagingItems.itemCount) { index ->
                    lazyPagingItems[index]?.let { pokemonSummary ->
                        PokemondexEntry(
                            entry = pokemonSummary,
                            modifier = Modifier.clickable {
                                onEventCalled(PokemonListEvent.OnPokemonSelected(pokemonSummary))
                            }
                        )
                    }
                }
            }
        }
    }
}