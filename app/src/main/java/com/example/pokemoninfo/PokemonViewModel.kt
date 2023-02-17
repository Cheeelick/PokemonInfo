package com.example.pokemoninfo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import com.example.pokemoninfo.api.Pokemon
import com.example.pokemoninfo.api.RetrofitClient


class PokemonViewModel(): BaseObservable() {

    var info: Pokemon? = null
        set(info){
            field = info
            notifyChange()
        }

    @get:Bindable
    val title: String? get() = info?.namePokemon


}