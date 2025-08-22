package com.yunho.smartrecompositiontutorial.cases

import androidx.compose.foundation.layout.Box
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

@Composable
fun InefficientCalculation(
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
fun OptimizedCalculation(
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
