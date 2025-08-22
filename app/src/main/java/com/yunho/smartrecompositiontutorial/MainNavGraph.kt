package com.yunho.smartrecompositiontutorial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yunho.smartrecompositiontutorial.cases.donutHoleSkipping
import com.yunho.smartrecompositiontutorial.cases.optimizedCalculation
import com.yunho.smartrecompositiontutorial.cases.positionalMemoization
import com.yunho.smartrecompositiontutorial.cases.stateLoop

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Route.Root
    ) {
        root(navController)
        optimizedCalculation()
        stateLoop()
        positionalMemoization()
        donutHoleSkipping()
    }
}
