package com.yunho.smartrecompositiontutorial.cases

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yunho.smartrecompositiontutorial.Case

@Composable
fun OptimizedCalculation(
    modifier: Modifier = Modifier
) {
    var case by remember { mutableStateOf(Case.PROBLEM) }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    case = if (case == Case.PROBLEM) Case.SOLUTION else Case.PROBLEM
                }
            ) {
                Text(if (case == Case.PROBLEM) "Show Solution" else "Show Problem")
            }
        }

        when (case) {
            Case.PROBLEM -> OptimizedCalculationProblem(modifier = Modifier.weight(1f))
            Case.SOLUTION -> OptimizedCalculationSolution(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun OptimizedCalculationProblem(
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
private fun OptimizedCalculationSolution(
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
