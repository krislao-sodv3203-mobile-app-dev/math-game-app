package com.krislao.mathgameapp.ui

data class GameUiState(
    val questionsCount: Int = 0,
    val totalQuestions: Int = 0,
    val addendOne: Int = 0,
    val addendTwo: Int = 0,
    val correctAnswers: Int = 0,
    val wrongAnswers: Int = 0,
)