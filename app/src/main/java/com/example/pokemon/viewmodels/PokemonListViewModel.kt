package com.example.pokemon.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.Constants.BASE_URL
import com.example.pokemon.models.PokemonListResponse
import com.example.pokemon.models.PokemonResult
import com.example.pokemon.services.PokemonApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class PokemonListViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonApiService = retrofit.create(PokemonApiService::class.java)

    val pokemonList = MutableLiveData<List<PokemonResult>>()

    fun getPokemonList(){
        val call = service.getPokemonList(100,0)

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)
                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                call.cancel()
            }

        })
    }
}