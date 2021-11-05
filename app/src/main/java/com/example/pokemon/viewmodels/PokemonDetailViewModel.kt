package com.example.pokemon.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.Constants.BASE_URL
import com.example.pokemon.models.PokemonDetailResponse
import com.example.pokemon.services.PokemonApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDetailViewModel : ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonApiService = retrofit.create(PokemonApiService::class.java)

    val pokemonDetail = MutableLiveData<PokemonDetailResponse>()

    fun getPokemonDetail(id: Int){
        val call = service.getPokemonDetail(id)

        call.enqueue(object : Callback<PokemonDetailResponse> {
            override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                response.body()?.let { pokemon ->
                    pokemonDetail.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                call.cancel()
            }

        })
    }
}