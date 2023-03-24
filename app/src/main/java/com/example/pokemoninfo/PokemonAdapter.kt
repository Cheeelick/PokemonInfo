package com.example.pokemoninfo

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemoninfo.api.Pokemon
import com.example.pokemoninfo.api.UrlPokemonPhoto
import com.example.pokemoninfo.databinding.ItemListPokemonFragmentBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

private const val TAG = "PokemonAdapter"

class PokemonAdapter(private val context: Context?):
    PagingDataAdapter<Any, PokemonViewHolder>(PokemonComparator){

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
//        holder.bind(getItem(position) as Pokemon?)
        holder.bindPhoto(getItem(position) as String)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = DataBindingUtil.inflate<ItemListPokemonFragmentBinding>(
                layoutInflater, R.layout.item_list_pokemon_fragment, parent, false)

        return PokemonViewHolder(binding)
    }
}




class PokemonViewHolder(private val binding: ItemListPokemonFragmentBinding)
    : RecyclerView.ViewHolder(binding.root){

//    fun bind(item: Pokemon?) {
//        with(binding){
//            namePokemon.text = item?.namePokemon
//        }
//    }

    fun bindPhoto(item: String?) {
        Picasso.get()
            .load(item)
            .into(binding.pokemonImage)


    }
}


private object PokemonComparator : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return (oldItem as Any) == (newItem as Any)
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return (oldItem as Pokemon) == (newItem as Pokemon)
    }
}