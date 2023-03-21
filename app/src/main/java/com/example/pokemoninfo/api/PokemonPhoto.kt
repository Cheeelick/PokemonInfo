package com.example.pokemoninfo.api

import com.squareup.moshi.Json

data class PokemonPhoto (
    @Json(name = "official-artwork")
    val official_artwork: UrlPokemonPhoto
)