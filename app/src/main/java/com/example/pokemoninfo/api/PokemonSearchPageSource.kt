package com.example.pokemoninfo.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemoninfo.model.PokemonResponse
import com.example.pokemoninfo.model.PokemonResponseDto


private const val TAG = "PokemonPageSource"

class PokemonSearchPageSource(
    private val service: ApiInterface,
    private val searchNamePokemon: String
) :PagingSource<Int, PokemonResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResponse> {
        return try {
            val page = params.key ?: 0
            val response = service.searchPokemon(searchNamePokemon)

            val result = mutableListOf<PokemonResponseDto>()
            result.add(response.body()!!)

//            Log.d(TAG, "!!!!!!!!!!Загружена страница ${page}!!!!!!!!!!!!!!!")

            LoadResult.Page(
                data = result.map { it.toPokemonResponse() },
                prevKey = if (page > 0) page - 1 else null,
                nextKey = null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, PokemonResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}