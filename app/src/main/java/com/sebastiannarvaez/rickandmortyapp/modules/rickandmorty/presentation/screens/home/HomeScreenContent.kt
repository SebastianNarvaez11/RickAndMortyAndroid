package com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.model.CharacterModel

@Composable
fun HomeScreenContent(
    rickListViewModel: RickListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val characters = rickListViewModel.characters.collectAsLazyPagingItems()

    when {
//        Carga inicial
        characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp), color = Color.Green)
            }
        }
        //      Vacio
        characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No hay datos")
            }
        }

        //      Error
        characters.loadState.hasError -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Ocurrio un error")
            }
        }

        else -> {
            Characterlist(
                characters
            )

            if (characters.loadState.append is LoadState.Loading) {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(64.dp), color = Color.Green)
                }
            }
        }
    }


}

@Composable
fun Characterlist(characters: LazyPagingItems<CharacterModel>) {
    LazyColumn {
        items(characters.itemCount) {
            characters[it]?.let { characterModel ->
                ItemList(
                    characterModel
                )
            }
        }
    }
}

@Composable
fun ItemList(character: CharacterModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(46.dp)
            .height(200.dp)
            .background(Color.Black)
    ) {
        AsyncImage(
            model = character.image,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = "Image"
        )
        Column {
            Text(text = character.name, color = Color.White)
            Text(text = character.image, color = Color.White)
        }

    }
}