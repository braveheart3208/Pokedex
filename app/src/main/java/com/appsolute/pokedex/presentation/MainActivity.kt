package com.appsolute.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.appsolute.pokedex.presentation.component.pokemonlist.PokemonListScreen
import com.appsolute.pokedex.presentation.event.UiEvent
import com.appsolute.pokedex.presentation.ui.theme.PokedexTheme
import com.appsolute.pokedex.presentation.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PokeListScreen.route
                    ) {
                        composable(route = Screen.PokeListScreen.route) {
                            val viewModel = hiltViewModel<PokemonListViewModel>()

                            LaunchedEffect(key1 = Unit) {
                                viewModel.uiEvent.collect { uiEvent ->
                                    when (uiEvent) {
                                        is UiEvent.Navigate -> {
                                            navController.navigate(uiEvent.route)
                                        }

                                        else -> {}
                                    }
                                }
                            }

                            val pagingItems =
                                viewModel.pagingPokemonItemFlow.collectAsLazyPagingItems()

                            pagingItems.itemSnapshotList.items
                            PokemonListScreen(
                                lazyPagingItems = pagingItems,
                                state = viewModel.state.value,
                                onEventCalled = viewModel::onEventCalled,
                            )
                        }
                        composable(
                            route = Screen.PokeDetailScreen.route + "/{pokemonName}",
                            arguments = listOf(
                                navArgument("dominantColor") {
                                    type = NavType.IntType
                                },
                                navArgument("pokemonName") {
                                    type = NavType.StringType
                                },
                            )
                        ) {
                            val dominantColor = remember {
                                val color = it.arguments?.getInt("dominantColor")
                                color?.let { Color(it) } ?: Color.White
                            }

                            val pokemonName = remember {
                                val name = it.arguments?.getInt("pokemonName")
                                name ?: "Unknown"
                            }
                        }
                    }
                }
            }
        }
    }
}