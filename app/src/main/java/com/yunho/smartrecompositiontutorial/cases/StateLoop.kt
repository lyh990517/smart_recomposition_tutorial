package com.yunho.smartrecompositiontutorial.cases

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

fun NavGraphBuilder.stateLoop() {
    composable<Route.StateLoop> {
        StateLoop(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun StateLoop(
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
    var count by remember { mutableIntStateOf(0) }

    Box(modifier = modifier) {
        Button(
            onClick = { count++ }
        ) {
            Text(text = "$count")

            count++ // don't change state on recomposition scope
        }
    }
}

@Composable
private fun Solution(
    modifier: Modifier = Modifier
) {
    var count by remember { mutableIntStateOf(0) }

    Box(modifier = modifier) {
        Button(
            onClick = { count++ }
        ) {
            Text(text = "$count")
        }
    }
}
