package com.example.pokemon

import android.R.attr
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.adapters.MyPokemonListAdapter
import com.example.pokemon.adapters.PokemonListAdapter
import com.example.pokemon.models.PokemonDetailResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import android.R.attr.data
import com.example.pokemon.Constants.MY_POKEMON_LIST
import com.example.pokemon.Constants.MY_SHARED_PREF
import com.example.pokemon.utils.MySharedPref
import java.util.*
import kotlin.Comparator


class MyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list)

        MySharedPref.init(this)

        initUI();
    }
    
    private fun initUI() {
        val pokemonList = MySharedPref.myPokemonList

        Log.d("TAG", "initUI: $pokemonList")

        rv_pokemon.layoutManager = LinearLayoutManager(this)
        rv_pokemon.adapter = MyPokemonListAdapter{
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        if (pokemonList != null) {
            (rv_pokemon.adapter as MyPokemonListAdapter).setData(pokemonList)
        }

    }

}