package com.krislao.mathgameapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.krislao.mathgameapp.ui.screens.StartScreen

@Composable
fun MathGameApp(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        StartScreen(modifier = Modifier.padding(innerPadding))
    }
}