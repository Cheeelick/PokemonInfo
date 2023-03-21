package com.example.pokemoninfo.api

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil


private const val TAG = "PokemonPageSource"

class PokemonPageSource(
    private val service: ApiInterface,
) :PagingSource<Int, Any>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        return try {
            val page = params.key ?: 0
            val pageSize = params.loadSize
            val pages = if (page == 0) 1 else page
            val response = service.getAllName(pages, page, pageSize)
            val urls = mutableListOf<String>()
            urls.add(response.sprites.other.official_artwork.urlPhoto)

            Log.d(TAG, "!!!!!!!!!!${page}!!!!!!!!${pages}!!!!!!!!!${response.sprites}!!!!!")

            if (page == 0){
                LoadResult.Page(emptyList(), null, pages)
            }else{
                LoadResult.Page(
                    data = urls,
                    prevKey = if (page > 0) page - 1 else null,
                    nextKey = if (page < 1281) page + 1 else null
                )
            }

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}