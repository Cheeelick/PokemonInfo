package com.example.pokemoninfo.model

import com.squareup.moshi.Json

data class PokemonPhotoDto (
    @Json(name = "official-artwork")
    val official_artwork: UrlPokemonPhotoDto
)