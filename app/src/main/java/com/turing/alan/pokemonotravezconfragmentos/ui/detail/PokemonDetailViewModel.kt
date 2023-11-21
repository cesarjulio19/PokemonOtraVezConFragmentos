package com.turing.alan.pokemonotravezconfragmentos.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonRepository
import kotlinx.coroutines.launch

class PokemonDetailViewModel(): ViewModel() {
    private val repository = PokemonRepository.getInstance()

    private val _pokemonUi = MutableLiveData<PokemonApiModel>()
    val pokemonUi: LiveData<PokemonApiModel>
        get() = _pokemonUi
    private val observer = Observer<PokemonApiModel> {
        val pokemon = PokemonApiModel(it.id, it.name, it.weight, it.height, it.front)
        _pokemonUi.value = pokemon
    }



      fun getPokemon(name:String) {
          repository.pokemonD.observeForever(observer)
         viewModelScope.launch {
              repository.getPokemon(name)

         }



      }

}