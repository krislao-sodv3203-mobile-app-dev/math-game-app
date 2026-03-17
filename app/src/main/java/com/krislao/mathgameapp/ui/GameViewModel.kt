package com.krislao.mathgameapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var inputTotalQuestions by mutableStateOf("")
        private set

    fun updateInputTotalQuestions(newInput: String) {
        inputTotalQuestions = newInput
    }

    fun startGame() {
        val questionsCount = inputTotalQuestions.toIntOrNull() ?: 0
        _uiState.update { currentState ->
            currentState.copy(
                totalQuestions = questionsCount,
                isGameStarted = true,
            )
        }
    }

    // helper function
    val isInputValid: Boolean
        get() = (inputTotalQuestions.toIntOrNull() ?: 0) > 0
}
