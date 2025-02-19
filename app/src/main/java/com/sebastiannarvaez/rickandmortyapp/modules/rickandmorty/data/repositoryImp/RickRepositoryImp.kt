package com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.data.repositoryImp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.data.CharacterPagingSource
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.data.RickMortyApiService
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.model.CharacterModel
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.repository.RickRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RickRepositoryImp @Inject constructor(val api: RickMortyApiService): RickRepository {

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = RickRepository.MAX_ITEMS,
                prefetchDistance = RickRepository.PREFETCH_ITEMS
            ),
            pagingSourceFactory = {
                CharacterPagingSource(api)
            }).flow
    }
}