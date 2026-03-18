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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.krislao.mathgameapp.ui.GameUiState
import com.krislao.mathgameapp.ui.theme.MathGameAppTheme

@Composable
fun QuestionsScreen(
    gameUiState: GameUiState,
    modifier: Modifier = Modifier,
    answer: String,
    onAnswerChange: (String) -> Unit = {},
    onCancel: () -> Unit = {},
    onAnswerSubmit: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
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
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${gameUiState.addendOne} + ${gameUiState.addendTwo} =",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedTextField(
                    value = answer,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() }) {
                            onAnswerChange(input)
                        }
                    },
                    label = { Text("Answer") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { onAnswerSubmit() }
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
                onClick = onCancel
            ) {
                Text(text = "Cancel")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                enabled = true,
                onClick = onAnswerSubmit
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
        QuestionsScreen(
            gameUiState = GameUiState(
                questionsCount = 3,
                totalQuestions = 10,
                addendOne = 2,
                addendTwo = 3,
                correctAnswers = 2,
                wrongAnswers = 1
            ),
            answer = "5",
        )
    }
}
