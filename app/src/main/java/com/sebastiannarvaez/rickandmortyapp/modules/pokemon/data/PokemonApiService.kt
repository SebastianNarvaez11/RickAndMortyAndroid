package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data

import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.responses.GetPokemonByNameResponse
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.responses.GetPokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
//    "/api/v2/pokemon?limit=10&offset=0"

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): GetPokemonsResponse

    @GET("/api/v2/pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name:String): GetPokemonByNameResponse
}