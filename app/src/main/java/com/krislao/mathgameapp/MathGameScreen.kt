package com.krislao.mathgameapp

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.krislao.mathgameapp.ui.GameViewModel
import com.krislao.mathgameapp.ui.screens.QuestionsScreen
import com.krislao.mathgameapp.ui.screens.ResultsScreen
import com.krislao.mathgameapp.ui.screens.StartScreen

enum class MathGameScreen() {
    Start,
    Questions,
    Results
}

@Composable
fun MathGameApp(
    viewModel: GameViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MathGameScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Call the composable() function once for each of the 3 routes.
            composable(route = MathGameScreen.Start.name) {
                // reset view model on screen load
                LaunchedEffect(Unit) {
                    viewModel.resetGame()
                }

                StartScreen(
                    totalQuestions = viewModel.inputTotalQuestions,
                    isInputValid = viewModel.isInputValid,
                    onInputChange = { viewModel.updateInputTotalQuestions(it) },
                    onGameStart = {
                        viewModel.startGame()
                        navController.navigate(MathGameScreen.Questions.name)
                    },
                    modifier = Modifier.padding(24.dp)
                )
            }

            composable(route = MathGameScreen.Questions.name) {
                // navigate to results screen if game is over
                LaunchedEffect(uiState.isGameOver) {
                    if (uiState.isGameOver) {
                        navController.navigate(MathGameScreen.Results.name)
                    }
                }

                QuestionsScreen(
                    gameUiState = uiState,
                    answer = viewModel.inputUserAnswer,
                    onAnswerChange = { viewModel.updateUserAnswer(it) },
                    onCancel = { navController.popBackStack() },
                    onAnswerSubmit = { viewModel.checkUserAnswer() },
                    modifier = Modifier.padding(24.dp)
                )
            }

            composable(route = MathGameScreen.Results.name) {
                ResultsScreen(
                    correctAnswers = uiState.correctAnswers,
                    wrongAnswers = uiState.wrongAnswers,
                    totalQuestions = uiState.totalQuestions,
                    onExit = { (context as? Activity)?.finish() },
                    onPlayAgain = {
                        navController.popBackStack(MathGameScreen.Start.name, inclusive = false)
                    },
                )
            }
        }

    }
}