package com.yunho.smartrecompositiontutorial.cases.advenced

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.yunho.smartrecompositiontutorial.base.Case
import com.yunho.smartrecompositiontutorial.base.Tutorial

fun NavGraphBuilder.stateDelegationUsingLambda() {
    composable<Route.StateDelegationUsingLambda> {
        StateDelegationUsingLambda(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun StateDelegationUsingLambda(
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

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.clickable { count++ },
            text = "click this"
        )

        ChildA(count = count)
    }
}

@Composable
private fun Solution(
    modifier: Modifier = Modifier
) {
    var count by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.clickable { count++ },
            text = "click this"
        )

        ChildB(count = { count })
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
    count: () -> Int,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "Count: ${count()}"
    )
}
