package com.turing.alan.pokemonotravezconfragmentos.ui.detail


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.turing.alan.pokemonotravezconfragmentos.R
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonApiModel
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonDetailBinding
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonListBinding
import com.turing.alan.pokemonotravezconfragmentos.ui.adapter.PokemonAdapter
import com.turing.alan.pokemonotravezconfragmentos.ui.list.PokemonListViewModel
import kotlinx.coroutines.launch

class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding
    private val args: PokemonDetailFragmentArgs by navArgs()
    private val viewModel: PokemonDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(inflater,
            container,
            false

        )
        return binding.root
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.topAppBar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        toolbar.setNavigationOnClickListener {

            findNavController().navigateUp()
        }
        viewModel.getPokemon(args.name)
         viewModel.pokemonUi.observe(viewLifecycleOwner){
             binding.nameText.text = it.name
             binding.heightText.text = it.height.toString() + " height"
             binding.weightText.text = it.weight.toString() + " weight"
             binding.image.load(it.front) {

                 placeholder(R.drawable.ic_launcher_background)
             }
         }

    }


}






