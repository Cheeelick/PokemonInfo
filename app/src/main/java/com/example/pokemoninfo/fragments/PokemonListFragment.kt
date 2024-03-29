package com.example.pokemoninfo.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemoninfo.PokemonAdapter
import com.example.pokemoninfo.R
import com.example.pokemoninfo.api.ApiInterface
import com.example.pokemoninfo.databinding.FragmentListPokemonBinding
import com.example.pokemoninfo.model.PokemonResponse
import com.example.pokemoninfo.model.PokemonType
import com.example.pokemoninfo.viewmodels.PassengerViewModel
import com.example.pokemoninfo.viewmodels.PassengerViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "PokemonListFragment"

class PokemonListFragment: Fragment(R.layout.fragment_list_pokemon) {

    private var callbacks: Callbacks? = null
    private lateinit var viewModel: PassengerViewModel
    private var _binding: FragmentListPokemonBinding? = null
    private val binding get() = _binding!!
    private val pokemonAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PokemonAdapter(context, callbacks)
    }

    interface Callbacks{
        fun onPokemonSelected(pokemon: PokemonResponse)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        val factory = PassengerViewModelFactory(ApiInterface())
        viewModel = ViewModelProviders.of(this, factory).get(PassengerViewModel::class.java)

        viewModel.pagingLiveData.observe(this) { pageData->
            pokemonAdapter.submitData(lifecycle, pageData)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        ceState: Bundle?
    ): View? {
        _binding = FragmentListPokemonBinding.inflate(inflater, container, false)

        binding.recyclerPokemon.apply{
            layoutManager = GridLayoutManager(context,2)
            adapter = pokemonAdapter
        }

        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_search_view_item, menu)

        val searchItem: MenuItem = menu.findItem(R.id.menu_item_search)
        val searchView = searchItem.actionView as SearchView

        searchView.apply{
            setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.setSearchPokemon(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_item_clear -> {
                viewModel.setSearchPokemon("")
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        fun newInstance(): PokemonListFragment{
            return PokemonListFragment()
        }
    }
}