package com.yunho.smartrecompositiontutorial.cases

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.yunho.smartrecompositiontutorial.cases.base.Case
import com.yunho.smartrecompositiontutorial.cases.base.CaseState

@Composable
fun StateLoop(
    modifier: Modifier = Modifier
) {
    Case(modifier = modifier) { case ->
        when (case) {
            CaseState.Case.Problem -> Problem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            CaseState.Case.Solution -> Solution(
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
