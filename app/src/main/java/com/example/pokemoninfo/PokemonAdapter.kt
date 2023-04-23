package com.example.pokemoninfo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemoninfo.model.PokemonResponse
import com.example.pokemoninfo.databinding.ItemListPokemonFragmentBinding
import com.example.pokemoninfo.fragments.PokemonListFragment
import com.squareup.picasso.Picasso

private const val TAG = "PokemonAdapter"

class PokemonAdapter(context: Context?, private val callbacks: PokemonListFragment.Callbacks?):
    PagingDataAdapter<PokemonResponse, PokemonViewHolder>(PokemonComparator){

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position) as PokemonResponse?)

        holder.itemView.setOnClickListener {
            callbacks?.onPokemonSelected(
                (getItem(position) as PokemonResponse)
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




private object PokemonComparator : DiffUtil.ItemCallback<PokemonResponse>() {

    override fun areItemsTheSame(oldItem: PokemonResponse, newItem: PokemonResponse): Boolean {
        return (oldItem as PokemonResponse) == (newItem as PokemonResponse)
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: PokemonResponse, newItem: PokemonResponse): Boolean {
        return (oldItem as PokemonResponse) == (newItem as PokemonResponse)
    }
}