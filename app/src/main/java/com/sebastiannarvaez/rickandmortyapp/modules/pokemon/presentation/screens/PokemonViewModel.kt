package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sebastiannarvaez.rickandmortyapp.core.network.ApiResponse
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonDetailModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.usecases.GetPokemonDetailUseCase
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.usecases.GetPokemonsUseCase
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.presentation.screens.detail.PokemonDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private var _detailState = MutableStateFlow<PokemonDetailState>(PokemonDetailState.Loading)
    val detailState: StateFlow<PokemonDetailState> = _detailState

    val characters: Flow<PagingData<PokemonModel>> = getPokemonsUseCase().cachedIn(viewModelScope)

    fun getPokemonDetail(name: String) {
        viewModelScope.launch {
            _detailState.value = PokemonDetailState.Loading
            val result = withContext(Dispatchers.IO) { getPokemonDetailUseCase(name) }

            _detailState.value = when (result) {
                is ApiResponse.Success -> PokemonDetailState.Success(pokemon = result.data)
                is ApiResponse.Error -> PokemonDetailState.Error(error = result.message)
            }
        }
    }
}