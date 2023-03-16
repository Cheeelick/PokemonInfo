package com.example.pokemoninfo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemoninfo.api.Pokemon
import com.example.pokemoninfo.databinding.ItemListPokemonFragmentBinding
import com.example.pokemoninfo.fragments.PokemonListFragment
import kotlinx.coroutines.NonDisposableHandle.parent

class PokemonAdapter(context: Context?):
    PagingDataAdapter<Pokemon, PokemonViewHolder>(PokemonComparator){

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = DataBindingUtil.inflate<ItemListPokemonFragmentBinding>(
                layoutInflater, R.layout.item_list_pokemon_fragment, parent, false)

        
        return PokemonViewHolder(binding)
    }
}


class PokemonViewHolder(private val binding: ItemListPokemonFragmentBinding)
    : RecyclerView.ViewHolder(binding.root){

    fun bind(item: Pokemon?) =
        with(binding){
            namePokemon.text = item?.namePokemon
        }
}


private object PokemonComparator : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.namePokemon == newItem.namePokemon
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}