package com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.di

import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.data.RickMortyApiService
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.data.repositoryImp.RickRepositoryImp
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.repository.RickRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyNetworkModule {

    private const val BASE_URL = "https://rickandmortyapi.com"

    @Provides
    @Singleton
    fun provideRickMortyApiService(retrofitBuilder: Retrofit.Builder): RickMortyApiService =
        retrofitBuilder
            .baseUrl(BASE_URL)
            .build()
            .create(RickMortyApiService::class.java)

    @Provides
    @Singleton
    fun provideRickRepository(api: RickMortyApiService): RickRepository =
        RickRepositoryImp(api)
}
