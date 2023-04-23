package com.example.pokemoninfo.model

import android.os.Parcel
import android.os.Parcelable

@Suppress("UNREACHABLE_CODE")
data class PokemonResponse(
    val sprites: Other,
    val name: String?,
    val types: List<PokemonType>
): Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("sprites"),
        parcel.readString(),
        TODO("types")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonResponse> {
        override fun createFromParcel(parcel: Parcel): PokemonResponse {
            return PokemonResponse(parcel)
        }

        override fun newArray(size: Int): Array<PokemonResponse?> {
            return arrayOfNulls(size)
        }
    }
}