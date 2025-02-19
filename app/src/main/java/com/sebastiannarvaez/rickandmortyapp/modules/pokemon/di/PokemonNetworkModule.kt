package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.di

import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.PokemonApiService
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.repositories.PokemonRepositoryImp
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.repositories.PokemonRepository
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
object PokemonNetworkModule {

    private const val BASE_URL = "https://pokeapi.co/"

    @Provides
    @Singleton
    fun providePokemonApiService(retrofitBuilder: Retrofit.Builder): PokemonApiService =
        retrofitBuilder
            .baseUrl(BASE_URL)
            .build()
            .create(PokemonApiService::class.java)


    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApiService): PokemonRepository =
        PokemonRepositoryImp(api)
}
