package com.yunho.smartrecompositiontutorial.cases.advenced

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yunho.smartrecompositiontutorial.Route
import com.yunho.smartrecompositiontutorial.base.Case
import com.yunho.smartrecompositiontutorial.base.Tutorial

fun NavGraphBuilder.statePassing() {
    composable<Route.StatePassing> {
        StatePassing(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun StatePassing(
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
    val count = remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.clickable { count.intValue++ },
            text = "click this"
        )

        // The moment you call the value of a state,
        // the composable begins tracking changes to that state, and recomposition occurs based on those changes.
        ChildA(count = count.intValue)
    }
}

@Composable
private fun Solution(
    modifier: Modifier = Modifier
) {
    val count = remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.clickable { count.intValue++ },
            text = "click this"
        )

        // Since we didn't call the value of the state,
        // the composable doesn't track the state and no recomposition occurs due to that state.
        ChildB(count = count)
        ChildC(count = count)
    }
}

@Composable
private fun ChildA(
    count: Int,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "Count: $count"
    )
}

@Composable
private fun ChildB(
    count: State<Int>,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "Count: ${count.value}" // read state on child
    )
}

@Composable
private fun ChildC(
    count: State<Int>,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "Count: $count" // Even though the state was passed, value was not called, so recomposition does not occur even if the state changes.
    )
}
