package com.davidspartan.pocketquiz.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidspartan.pocketquiz.model.api.ApiRepository
import com.davidspartan.pocketquiz.model.api.SimplePokemon
import com.davidspartan.pocketquiz.model.game.GameLogic
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel: ViewModel() {
    private  val gameLogic = GameLogic()

    private val _score = MutableLiveData<Int>(5)
    val score: LiveData<Int> = _score
    private val _options = MutableLiveData<List<String>>(List(4) { "" })
    val options: LiveData<List<String>> = _options

    fun generateOptions(correctOption: SimplePokemon) {
        _options.value = gameLogic.generateOptions(correctOption)
    }
    fun onAnswer(selectedOption: String,correctAnswer:String ) {

        if (selectedOption == correctAnswer){
            _score.value = _score.value?.plus(1)
        }else {
            viewModelScope.launch {
                _score.value = _score.value?.minus(1)
            }
        }
    }
}