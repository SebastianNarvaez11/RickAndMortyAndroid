package com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.data

import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.data.responses.GetCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApiService {
    @GET("/api/character/")
    suspend fun getCharacters(@Query("page") page: Int): GetCharactersResponse
}