package com.yunho.smartrecompositiontutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.root(navController: NavHostController) {
    composable<Root> {
        Root(
            navController = navController,
            modifier = Modifier.fillMaxSize()
        )
    }
}

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
