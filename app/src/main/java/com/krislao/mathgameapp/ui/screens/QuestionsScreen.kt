package com.krislao.mathgameapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krislao.mathgameapp.ui.GameViewModel
import com.krislao.mathgameapp.ui.theme.MathGameAppTheme

@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(),
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        GameStatus(
            correctAnswers = gameUiState.correctAnswers,
            wrongAnswers = gameUiState.wrongAnswers,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Question ${gameUiState.questionsCount} of ${gameUiState.totalQuestions}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${gameUiState.addendOne} + ${gameUiState.addendTwo} =",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedTextField(
                    value = gameViewModel.inputUserAnswer,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() }) {
                            gameViewModel.updateUserAnswer(input)
                        }
                    },
                    label = { Text("Answer") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 8.dp)
                )
            }
        }

        Row {
            OutlinedButton(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                onClick = {}
            ) {
                Text(text = "Cancel")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                enabled = true,
                onClick = { gameViewModel.checkUserAnswer() }
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun GameStatus(
    correctAnswers: Int,
    wrongAnswers: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Correct: $correctAnswers",
            color = Color(0xFF4CAF50),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Wrong: $wrongAnswers",
            color = Color.Red,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview() {
    MathGameAppTheme {
        QuestionsScreen()
    }
}
