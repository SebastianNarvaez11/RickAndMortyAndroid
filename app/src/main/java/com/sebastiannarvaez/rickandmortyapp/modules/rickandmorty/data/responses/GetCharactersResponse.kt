package com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.data.responses

import com.google.gson.annotations.SerializedName

data class GetCharactersResponse(
    @SerializedName("info") val information: InfoResponse,
    @SerializedName("results") val results: List<ItemCharacterResponse>
)
