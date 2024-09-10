package com.davidspartan.pocketquiz.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidspartan.pocketquiz.model.api.ApiRepository
import com.davidspartan.pocketquiz.model.api.SimplePokemon
import kotlinx.coroutines.launch
import kotlin.random.Random

class PokemonViewModel: ViewModel() {
    private val repository = ApiRepository()

    private val _pokemon = MutableLiveData<SimplePokemon>()
    val pokemon: LiveData<SimplePokemon> = _pokemon

    fun fetchRandomPokemon() {
        viewModelScope.launch {
            try {
                _pokemon.value = repository.getPokemon(Random.nextInt(1, 722))
            } catch (e: Exception) {
                Log.d("Error fetching pokemon", e.toString())
            }
        }
    }
}