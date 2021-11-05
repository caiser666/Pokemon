package com.example.pokemon.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.pokemon.Constants.MY_POKEMON_LIST
import com.example.pokemon.Constants.MY_SHARED_PREF
import com.example.pokemon.models.PokemonDetailResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object MySharedPref {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE)
    }

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var myPokemonList: MutableList<PokemonDetailResponse>?
        get() = getMyPokemonList()
        set(value) {
            setMyPokemonList(value)
        }

    @JvmName("getMyPokemonList1")
    private fun getMyPokemonList(): MutableList<PokemonDetailResponse>? {
        val gson = Gson()
        val myPokemonList = sharedPreferences.getString(MY_POKEMON_LIST, "[]")
        val jsonType = object : TypeToken<MutableList<PokemonDetailResponse>>() {}.type
        return gson.fromJson(myPokemonList, jsonType)
    }

    @JvmName("setMyPokemonList1")
    private fun setMyPokemonList(value: MutableList<PokemonDetailResponse>?) {
        val gson = Gson()
        val myPokemonList = gson.toJson(value)

        sharedPreferences.editMe {
            it.putString(MY_POKEMON_LIST, myPokemonList)
        }
    }
}