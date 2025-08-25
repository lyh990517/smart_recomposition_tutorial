package com.yunho.smartrecompositiontutorial.cases.advenced

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yunho.smartrecompositiontutorial.Route
import com.yunho.smartrecompositiontutorial.base.Case
import com.yunho.smartrecompositiontutorial.base.Tutorial

fun NavGraphBuilder.stateHoisting() {
    composable<Route.StateHoisting> {
        StateHoisting(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun StateHoisting(
    modifier: Modifier = Modifier
) {
    Tutorial(modifier = modifier) { case ->
        when (case) {
            Case.Problem -> Problem(
                modifier = Modifier.fillMaxWidth()
            )

            Case.Solution -> Solution(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Problem(
    modifier: Modifier = Modifier
) {
    // If you only have unused state and only pass it to child composable, then don't bother with state hoisting.
    // This may cause unnecessary recomposition.
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Problem: unused state hoisted to parent component",
            modifier = Modifier.padding(16.dp)
        )

        // If you use the hoisted state in the parent, then the state hoisting is correct.
        // Text(text = "count: $count")

        // You should only go stateless when necessary. It's not always good to go stateless.
        ChildWithHoistedState(
            count = count,
            onIncrement = { count++ },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun Solution(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Solution: Don't hoist or only hoist the state to where it is used.",
            modifier = Modifier.padding(16.dp)
        )

        ChildWithLocalState(
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun ChildWithLocalState(
    modifier: Modifier = Modifier
) {
    var localCount by remember { mutableIntStateOf(0) }

    Button(
        onClick = { localCount++ },
        modifier = modifier
    ) {
        Text("Local Count: $localCount")
    }
}

@Composable
private fun ChildWithHoistedState(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onIncrement,
        modifier = modifier
    ) {
        Text("Hoisted Count: $count")
    }
}
