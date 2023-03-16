package com.example.pokemoninfo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pokemoninfo.api.ApiInterface
import com.example.pokemoninfo.api.PokemonPageSource

class PassengerViewModel(
    private val service: ApiInterface
): ViewModel() {

    val passengers =
        Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            PokemonPageSource(service)
        }).flow.cachedIn(viewModelScope)
}