package com.krislao.mathgameapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

private const val TAG = "GameViewModel"

class GameViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var inputTotalQuestions by mutableStateOf("")
        private set
    var inputUserAnswer by mutableStateOf("")
        private set

    fun updateInputTotalQuestions(newInput: String) {
        inputTotalQuestions = newInput
    }

    fun updateUserAnswer(newInput: String) {
        inputUserAnswer = newInput
    }

    fun checkUserAnswer() {
        val userAnswer = inputUserAnswer.toIntOrNull() ?: 0
        val correctAnswer = with(_uiState.value) { addendOne + addendTwo }
        val isCorrect = userAnswer == correctAnswer

        updateGameStatus(isCorrect)

        if (with(_uiState.value) { questionsCount < totalQuestions }) {
            generateNewQuestion()
        } else {
            // TODO:
            Log.d(TAG, "Proceed to Results Screen")
        }
        inputUserAnswer = ""
    }

    fun startGame() {
        val questionsCount = inputTotalQuestions.toIntOrNull() ?: 0
        _uiState.update { currentState ->
            currentState.copy(
                totalQuestions = questionsCount,
            )
        }

        generateNewQuestion()
    }

    fun resetGame() {
        _uiState.value = GameUiState()
    }

    private fun generateNewQuestion() {
        _uiState.update { currentState ->
            currentState.copy(
                questionsCount = currentState.questionsCount + 1,
                addendOne = Random.nextInt(1, 21),
                addendTwo = Random.nextInt(1, 21)
            )
        }
    }

    private fun updateGameStatus(isCorrect: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                // Increment correctAnswers if isCorrect is true, otherwise keep current value
                correctAnswers = if (isCorrect) currentState.correctAnswers + 1 else currentState.correctAnswers,
                // Increment wrongAnswers if isCorrect is false, otherwise keep current value
                wrongAnswers = if (!isCorrect) currentState.wrongAnswers + 1 else currentState.wrongAnswers
            )
        }
    }

    // helper function
    val isInputValid: Boolean
        get() = (inputTotalQuestions.toIntOrNull() ?: 0) > 0
}
