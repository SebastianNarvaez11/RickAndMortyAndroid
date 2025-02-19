package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonDetailModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.presentation.screens.PokemonViewModel

@Composable
fun PokemonDetailScreen(name: String, viewModel: PokemonViewModel = hiltViewModel()) {
    val state by viewModel.detailState.collectAsState()

    LaunchedEffect(name) {
        viewModel.getPokemonDetail(name)
    }

    Column {
        when (val currentState = state) {
            is PokemonDetailState.Loading -> LoadingPokemon()
            is PokemonDetailState.Error -> ErrorPokemon(currentState.error)
            is PokemonDetailState.Success -> PokemonDetailView(currentState.pokemon)
        }
    }
}


@Composable
fun PokemonDetailView(pokemon: PokemonDetailModel) {
    Column() {
        Text(text = pokemon.name)
        Text(text = pokemon.id.toString())

        LazyColumn {
            items(pokemon.abilities) { ability ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                ) {
                    Column {
                        Text(text = ability.name)
                        Text(text = ability.url)
                        Text(text = ability.isHidden.toString())
                    }
                }
            }
        }
    }
}


@Composable
fun LoadingPokemon() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color.Red)
    }
}

@Composable
fun ErrorPokemon(error: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = error)
    }
}