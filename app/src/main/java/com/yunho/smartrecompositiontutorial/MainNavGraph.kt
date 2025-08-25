package com.yunho.smartrecompositiontutorial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yunho.smartrecompositiontutorial.cases.basic.donutHoleSkipping
import com.yunho.smartrecompositiontutorial.cases.basic.optimizedCalculation
import com.yunho.smartrecompositiontutorial.cases.basic.positionalMemoization
import com.yunho.smartrecompositiontutorial.cases.basic.stateLoop
import com.yunho.smartrecompositiontutorial.cases.stateDelegation
import com.yunho.smartrecompositiontutorial.cases.stateDelegationUsingLambda

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
        stateDelegation()
        stateDelegationUsingLambda()
    }
}
