package com.example.pokemoninfo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokemoninfo.viewmodels.PokemonViewModel
import com.example.pokemoninfo.R
import com.example.pokemoninfo.api.Pokemon
import com.example.pokemoninfo.api.RetrofitClient
import com.example.pokemoninfo.databinding.FragmentListPokemonBinding
import com.example.pokemoninfo.databinding.ItemListPokemonFragmentBinding
import com.example.pokemoninfo.viewmodels.RetrofitViewModel

private const val TAG = "PokemonListFragment"

class PokemonListFragment: Fragment() {


    private var _binding: FragmentListPokemonBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RetrofitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RetrofitViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPokemonBinding.inflate(inflater, container, false)

        val view = binding.root

        binding.recyclerPokemon.apply{
            layoutManager = GridLayoutManager(context,2)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseLiveData.observe(
            viewLifecycleOwner,
            Observer { requestMessage ->
                binding.recyclerPokemon.apply {
                    Log.d(TAG, "Request recuived ${requestMessage}")
                    adapter = PokemonAdapter(requestMessage)
                }
            }
        )
    }

    private inner class PokemonAdapter(private val requestMessage: List<Pokemon>): RecyclerView.Adapter<PokemonHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
            val binding = DataBindingUtil.inflate<ItemListPokemonFragmentBinding>(
                layoutInflater, R.layout.item_list_pokemon_fragment, parent, false)
            return PokemonHolder(binding)
        }

        override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
            val infoPokemon = requestMessage[position]
            holder.bind(infoPokemon)
        }

        override fun getItemCount() = requestMessage.size

    }


    private inner class PokemonHolder(private val binding: ItemListPokemonFragmentBinding)
        : RecyclerView.ViewHolder(binding.root){
        init{
              binding.viewModelBinding = PokemonViewModel()
        }

        fun bind(result: Pokemon){
            binding.apply{
                viewModelBinding?.info = result
                executePendingBindings()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        fun newInstance(): PokemonListFragment{
            return PokemonListFragment()
        }
    }
}