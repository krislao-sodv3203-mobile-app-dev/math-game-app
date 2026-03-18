package com.krislao.mathgameapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.krislao.mathgameapp.ui.theme.MathGameAppTheme

@Composable
fun ResultsScreen(
    correctAnswers: Int,
    wrongAnswers: Int,
    totalQuestions: Int,
    modifier: Modifier = Modifier,
    onExitGame: () -> Unit = {},
    onPlayAgain: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Game Results", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(48.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Final Score", style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "$correctAnswers / $totalQuestions",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Correct", style = MaterialTheme.typography.labelLarge)
                        Text(
                            text = "$correctAnswers",
                            color = Color(0xFF4CAF50),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Wrong", style = MaterialTheme.typography.labelLarge)
                        Text(
                            text = "$wrongAnswers",
                            color = Color.Red,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(54.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Secondary Action
            OutlinedButton(
                onClick = onExitGame,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Text("Exit Game")
            }
            // Primary Action
            Button(
                onClick = onPlayAgain,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Text("Play Again")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    MathGameAppTheme {
        ResultsScreen(
            correctAnswers = 2,
            wrongAnswers = 1,
            totalQuestions = 3
        )
    }
}
