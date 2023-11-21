package com.turing.alan.pokemonotravezconfragmentos.data.api

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface  PokemonApi {
    @GET("api/v2/pokemon/{name}/")
    suspend fun fetchPokemon(@Path("name") name:String):PokemonDetailResponse
    // TODO Añadir nuevo metodo para leer una lista de pokemon
    @GET("api/v2/pokemon/")
    suspend fun fetchAllPokemon():PokemonListResponse
}


class PokemonRepository private constructor(private val api:PokemonApi) {
    private val _pokemon = MutableLiveData<PokemonListApiModel>()
    val pokemon: LiveData<PokemonListApiModel>
        get() = _pokemon

    private val _pokemonD = MutableLiveData<PokemonApiModel>()
    val pokemonD: LiveData<PokemonApiModel>
        get() = _pokemonD

    companion object {
        private var _INSTANCE: PokemonRepository? = null
        fun getInstance(): PokemonRepository {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val pokemonApi = retrofit.create(PokemonApi::class.java)
            _INSTANCE = _INSTANCE ?: PokemonRepository(pokemonApi)
            return _INSTANCE!!

        }
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun fetch() {
        //val pokemonResponse = api.fetchPokemon("1")
        // TODO Llamar a la api para obtener la lista
        val ListResponse = api.fetchAllPokemon()
        // TODO Recorrer la respuesta y llamar a la api de detalles
        val list:MutableList<PokemonApiModel> = mutableListOf()
        ListResponse.results.forEach(){
            val DetailResponse:PokemonDetailResponse = api.fetchPokemon(it.name)

            val pokemonApiModel = PokemonApiModel(DetailResponse.id,
                DetailResponse.name,
                DetailResponse.weight,DetailResponse.height,DetailResponse.sprites.front_default)
            list.add(pokemonApiModel)


        }

        // TODO Mapear el resultado a PokemonListApiModel
        //_pokemon.value = pokemonResponse
        val pokemonListApiModel = PokemonListApiModel(list)

        _pokemon.value = pokemonListApiModel
    }
    @SuppressLint("SuspiciousIndentation")
    suspend fun getPokemon(name:String) {
        val DetailResponse:PokemonDetailResponse = api.fetchPokemon(name)
        val pokemonApiModel = PokemonApiModel(DetailResponse.id,
            DetailResponse.name,
            DetailResponse.weight,
            DetailResponse.height,
            DetailResponse.sprites.front_default)
        _pokemonD.value = pokemonApiModel
    }


}