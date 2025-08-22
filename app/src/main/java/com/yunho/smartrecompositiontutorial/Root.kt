package com.yunho.smartrecompositiontutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Root(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigateToOptimizedCalculation() }
        ) {
            Text("OptimizedCalculation")
        }
        Button(
            onClick = { navController.navigateToStateLoop() }
        ) {
            Text("StateLoop")
        }
        Button(
            onClick = { navController.navigateToPositionalMemoization() }
        ) {
            Text("PositionalMemoization")
        }
        Button(
            onClick = { navController.navigateToDonutHoleSkipping() }
        ) {
            Text("DonutHoleSkipping")
        }
    }
}
