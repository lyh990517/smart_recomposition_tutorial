package com.yunho.smartrecompositiontutorial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yunho.smartrecompositiontutorial.cases.InefficientCalculation

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Root
    ) {
        composable<Root> {
            Root(
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable<Calculation> {
            InefficientCalculation(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
