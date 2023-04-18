package com.example.pokemoninfo.api

import com.example.pokemoninfo.model.*


internal fun PokemonResponseDto.toPokemonResponse(): PokemonResponse {
    return PokemonResponse(
        sprites = this.sprites.toOther(),
        name = name,
        types = this.types.map{it.toTypeName()}
    )
}

private fun OtherDto.toOther(): Other {
    return Other(
        other = this.other.toPokemonPhoto()
    )
}

private fun PokemonPhotoDto.toPokemonPhoto(): PokemonPhoto {
    return PokemonPhoto(
        official_artwork = this.official_artwork.toUrlPokemonPhoto()
    )
}

private fun UrlPokemonPhotoDto.toUrlPokemonPhoto(): UrlPokemonPhoto {
    return UrlPokemonPhoto(
        urlPhoto = urlPhoto
    )
}

private fun PokemonTypeDto.toTypeName(): PokemonType {
    return PokemonType(
        type = this.type.toType()
    )
}

private fun TypeDto.toType(): Type {
    return Type(
        typeName = typeName
    )
}