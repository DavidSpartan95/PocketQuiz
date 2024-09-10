package com.davidspartan.pocketquiz.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidspartan.pocketquiz.model.api.ApiRepository
import com.davidspartan.pocketquiz.model.api.SimplePokemon
import com.davidspartan.pocketquiz.model.game.GameLogic
import kotlinx.coroutines.launch
import kotlin.random.Random
enum class GameState {
    LOADING, PLAYING, RESULT, RELOAD
}
class GameViewModel: ViewModel() {
    private  val gameLogic = GameLogic()
    private val repository = ApiRepository()


    private val _pokemon = MutableLiveData<SimplePokemon>()
    val pokemon: LiveData<SimplePokemon> = _pokemon

    private val _score = MutableLiveData<Int>(0)
    val score: LiveData<Int> = _score

    private val _options = MutableLiveData<List<String>>(List(4) { "" })
    val options: LiveData<List<String>> = _options

    private val _gameState = MutableLiveData<GameState>(GameState.LOADING)
    val gameState: LiveData<GameState> = _gameState


    fun startGame(){
        //_gameState.value = GameState.LOADING
        if (gameState.value == GameState.RESULT){
            _gameState.value = GameState.RELOAD
        }
        fetchRandomPokemon()
    }
    fun generateOptions(correctOption: SimplePokemon) {
        _options.value = gameLogic.generateOptions(correctOption)
    }
    fun onAnswer(selectedOption: String,correctAnswer:String ):Boolean {

        if (selectedOption == correctAnswer.capitalize()){
            _score.value = _score.value?.plus(1)
            _gameState.value = GameState.RESULT
            return true
        }else {
            _score.value = _score.value?.minus(1)
            _gameState.value = GameState.RESULT
            return false

        }

    }
    private fun fetchRandomPokemon() {
        viewModelScope.launch {
            try {
                // Fetch a random Pokémon
                val randomPokemon = repository.getPokemon(Random.nextInt(1, 722))
                // Update LiveData with the fetched Pokémon
                _pokemon.value = randomPokemon
                // Automatically generate options based on the fetched Pokémon
                generateOptions(randomPokemon)
                _gameState.value = GameState.PLAYING
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error fetching Pokémon", e)
            }
        }
    }
}