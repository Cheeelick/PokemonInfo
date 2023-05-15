package com.example.pokemoninfo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemoninfo.model.PokemonResponse
import com.example.pokemoninfo.fragments.PokemonListFragment
import com.example.pokemoninfo.ui.PokemonListView
import com.squareup.picasso.Picasso

private const val TAG = "PokemonAdapter"

class PokemonAdapter(context: Context?, private val callbacks: PokemonListFragment.Callbacks?):
    PagingDataAdapter<PokemonResponse, PokemonViewHolder>(PokemonComparator){

    private val layoutInflater  : LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            callbacks?.onPokemonSelected(
                (getItem(position) as PokemonResponse)
            )
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list_pokemon_fragment, viewGroup, false)

        return PokemonViewHolder(view)
    }
}


class PokemonViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val pokemonsUi = itemView.findViewById<PokemonListView>(R.id.pokemonListView)

    fun bind(item: PokemonResponse?) {
        pokemonsUi.pokemonType = item!!.types[0].type.typeName
        pokemonsUi.pokemonPhoto = item.sprites.other.official_artwork.urlPhoto
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