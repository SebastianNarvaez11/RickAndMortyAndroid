package com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.model.CharacterModel
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RickListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val characters: Flow<PagingData<CharacterModel>> = getCharactersUseCase()
}