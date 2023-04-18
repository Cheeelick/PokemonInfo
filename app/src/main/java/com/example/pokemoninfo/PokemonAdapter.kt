package com.example.pokemoninfo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemoninfo.model.PokemonResponse
import com.example.pokemoninfo.databinding.ItemListPokemonFragmentBinding
import com.example.pokemoninfo.databinding.ItemListPokemonFragmentBindingImpl
import com.example.pokemoninfo.fragments.PokemonListFragment
import com.example.pokemoninfo.model.PokemonResponseDto
import com.squareup.picasso.Picasso

private const val TAG = "PokemonAdapter"

class PokemonAdapter(context: Context?, private val callbacks: PokemonListFragment.Callbacks?):
    PagingDataAdapter<Any, PokemonViewHolder>(PokemonComparator){

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position) as PokemonResponse?)

        holder.itemView.setOnClickListener {
            callbacks?.onCrimeSelected(
                (getItem(position) as PokemonResponse).name,
                (getItem(position) as PokemonResponse).sprites.other.official_artwork.urlPhoto,
                (getItem(position) as PokemonResponse).types
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = DataBindingUtil.inflate<ItemListPokemonFragmentBinding>(
                layoutInflater, R.layout.item_list_pokemon_fragment, parent, false)

        return PokemonViewHolder(binding)
    }
}


class PokemonViewHolder(private val binding: ItemListPokemonFragmentBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PokemonResponse?) {
        with(binding){
            Picasso.get()
                .load(item?.sprites?.other?.official_artwork?.urlPhoto)
                .into(binding.pokemonImage)

            namePokemon.text = item?.name
        }
    }
}




private object PokemonComparator : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return (oldItem as Any) == (newItem as Any)
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return (oldItem as Any) == (newItem as Any)
    }
}