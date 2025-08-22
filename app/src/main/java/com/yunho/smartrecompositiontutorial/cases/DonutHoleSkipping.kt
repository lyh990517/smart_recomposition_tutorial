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

fun NavGraphBuilder.donutHoleSkipping() {
    composable<Route.DonutHoleSkipping> {
        DonutHoleSkipping(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun DonutHoleSkipping(
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
    val itemList = remember { List(10) { it } }
    var recomposeCount by remember { mutableIntStateOf(0) }

    Box(modifier = modifier) {
        println("read state on box : $recomposeCount")

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
    val itemList = remember { List(10) { it } }
    var recomposeCount by remember { mutableIntStateOf(0) }

    Box(modifier = modifier) {
        LazyColumn {
            items(items = itemList) {
                Text(text = it.toString())
            }
        }

        Button(
            onClick = { recomposeCount++ }
        ) {
            println("read state only on button : $recomposeCount")

            Text(text = "recompose $recomposeCount")
        }
    }
}
