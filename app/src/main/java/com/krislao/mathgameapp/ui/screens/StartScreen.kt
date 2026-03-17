package com.krislao.mathgameapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krislao.mathgameapp.ui.GameViewModel
import com.krislao.mathgameapp.ui.theme.MathGameAppTheme

@Composable
fun StartScreen(
    gameViewModel: GameViewModel = viewModel(),
    modifier: Modifier
) {
    StartScreenContent(
        totalQuestions = gameViewModel.inputTotalQuestions,
        isInputValid = gameViewModel.isInputValid,
        onInputChange = { gameViewModel.updateInputTotalQuestions(it) },
        onGameStart = { gameViewModel.startGame() },
        modifier = modifier
    )
}

@Composable
fun StartScreenContent(
    totalQuestions: String,
    isInputValid: Boolean,
    modifier: Modifier = Modifier,
    onInputChange: (String) -> Unit = {},
    onGameStart: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Math Game",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Enter number of addition questions",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = totalQuestions,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    onInputChange(input)
                }
            },
            label = { Text("Number of Questions") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onGo = {
                    if (isInputValid) {
                        onGameStart()
                    }
                }
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(54.dp))
        Button(
            onClick = { onGameStart() },
            enabled = isInputValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Start Game",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    MathGameAppTheme {
        StartScreenContent(
            totalQuestions = "2",
            isInputValid = false,
        )
    }
}
