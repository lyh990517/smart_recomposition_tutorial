package com.yunho.smartrecompositiontutorial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yunho.smartrecompositiontutorial.cases.advenced.classStabilityInference
import com.yunho.smartrecompositiontutorial.cases.advenced.contentType
import com.yunho.smartrecompositiontutorial.cases.advenced.phaseSkipping
import com.yunho.smartrecompositiontutorial.cases.advenced.stateCalculationWithDerivedStateOf
import com.yunho.smartrecompositiontutorial.cases.advenced.stateCalculationWithSnapshotFlow
import com.yunho.smartrecompositiontutorial.cases.advenced.stateHoisting
import com.yunho.smartrecompositiontutorial.cases.advenced.statePassing
import com.yunho.smartrecompositiontutorial.cases.advenced.deferReadsAsLongAsPossible
import com.yunho.smartrecompositiontutorial.cases.basic.calculationCachingWithRemember
import com.yunho.smartrecompositiontutorial.cases.advenced.donutHoleSkipping
import com.yunho.smartrecompositiontutorial.cases.basic.positionalMemoization
import com.yunho.smartrecompositiontutorial.cases.basic.avoidingBackwardsWrites

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
        calculationCachingWithRemember()
        avoidingBackwardsWrites()
        positionalMemoization()
        donutHoleSkipping()
        statePassing()
        deferReadsAsLongAsPossible()
        phaseSkipping()
        stateCalculationWithDerivedStateOf()
        stateCalculationWithSnapshotFlow()
        stateHoisting()
        classStabilityInference()
        contentType()
    }
}
