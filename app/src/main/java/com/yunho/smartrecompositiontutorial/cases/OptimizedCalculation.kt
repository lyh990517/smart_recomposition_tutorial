package com.yunho.smartrecompositiontutorial.cases

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yunho.smartrecompositiontutorial.Route
import com.yunho.smartrecompositiontutorial.cases.base.Case
import com.yunho.smartrecompositiontutorial.cases.base.Tutorial

fun NavGraphBuilder.optimizedCalculation() {
    composable<Route.OptimizedCalculation> {
        OptimizedCalculation(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun OptimizedCalculation(
    modifier: Modifier = Modifier
) {
    Tutorial(modifier = modifier) { case ->
        when (case) {
            Case.Problem -> Problem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            Case.Solution -> Solution(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Problem(
    modifier: Modifier = Modifier
) {
    val itemList = List(10) { it }.sortedByDescending {
        println("Sorting item: $it")
        it
    }
    var recomposeCount by remember { mutableIntStateOf(0) }

    Box(modifier = modifier) {
        println("prevent donut hole skipping : $recomposeCount") // I will cover this in detail in the donut hole skipping section.

        LazyColumn {
            items(items = itemList) {
                Text(text = it.toString())
            }
        }

        Button(
            onClick = { recomposeCount++ }
        ) {
            Text(text = "recompose $recomposeCount")
        }
    }
}

@Composable
private fun Solution(
    modifier: Modifier = Modifier
) {
    val itemList = remember {
        List(10) { it }.sortedByDescending {
            println("Sorting item: $it")
            it
        }
    }
    var recomposeCount by remember { mutableIntStateOf(0) }

    Box(modifier = modifier) {
        println("prevent donut hole skipping : $recomposeCount")  // I will cover this in detail in the donut hole skipping section.

        LazyColumn {
            items(items = itemList) {
                Text(text = it.toString())
            }
        }

        Button(
            onClick = { recomposeCount++ }
        ) {
            Text(text = "recompose $recomposeCount")
        }
    }
}
