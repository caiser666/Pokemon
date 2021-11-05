package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.adapters.MyPokemonListAdapter
import com.example.pokemon.utils.MySharedPref
import kotlinx.android.synthetic.main.activity_my_list.*


class MyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list)

        MySharedPref.init(this)

        initUI()
    }
    
    private fun initUI() {
        val pokemonList = MySharedPref.myPokemonList

        //Log.d("TAG", "initUI: $pokemonList")

        rv_my_pokemon_list.layoutManager = LinearLayoutManager(this)
        rv_my_pokemon_list.adapter = MyPokemonListAdapter{
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        if (pokemonList != null) {
            (rv_my_pokemon_list.adapter as MyPokemonListAdapter).setData(pokemonList)
        }

    }

}