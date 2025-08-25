package com.yunho.smartrecompositiontutorial.cases.advenced

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
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

data class UnstableData(
    val name: String,
    val value: Int,
    var mutableList: MutableList<String> = mutableListOf()
)

@Stable
data class StableData(
    val name: String,
    val value: Int,
    val list: List<String> = emptyList()
)

@Immutable
data class ImmutableData(
    val name: String,
    val value: Int,
    val list: List<String> = emptyList()
)

fun NavGraphBuilder.classStabilityInference() {
    composable<Route.ClassStabilityInference> {
        ClassStabilityInference(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ClassStabilityInference(
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
    var counter by remember { mutableIntStateOf(0) }
    val unstableData = remember {
        UnstableData(
            name = "Unstable",
            value = 42,
            mutableList = mutableListOf("item1", "item2")
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Problem: Unstable class causes unnecessary recompositions",
            modifier = Modifier.padding(16.dp)
        )

        Text("Recomposition: $counter")

        Button(
            onClick = { counter++ }
        ) {
            Text("Trigger")
        }

        // This will recompose even when unstableData hasn't changed
        UnstableComponent(
            data = unstableData,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun Solution(
    modifier: Modifier = Modifier
) {
    var counter by remember { mutableIntStateOf(0) }
    val stableData = remember {
        StableData(
            name = "Stable",
            value = 42,
            list = listOf("item1", "item2")
        )
    }

    val immutableData = remember {
        ImmutableData(
            name = "Immutable",
            value = 42,
            list = listOf("item1", "item2")
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Solution: Use @Stable/@Immutable annotations for better performance",
            modifier = Modifier.padding(16.dp)
        )

        Text("Recomposition: $counter")

        Button(
            onClick = { counter++ }
        ) {
            Text("Trigger")
        }

        // These won't recompose unnecessarily
        StableComponent(
            data = stableData,
            modifier = Modifier.padding(8.dp)
        )

        ImmutableComponent(
            data = immutableData,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun UnstableComponent(
    data: UnstableData,
    modifier: Modifier = Modifier
) {
    println("UnstableComponent recomposed with: ${data.name}")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unstable: ${data.name}")
        Text("Value: ${data.value}")
        Text("List size: ${data.mutableList.size}")
    }
}

@Composable
private fun StableComponent(
    data: StableData,
    modifier: Modifier = Modifier
) {
    println("StableComponent recomposed with: ${data.name}")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Stable: ${data.name}")
        Text("Value: ${data.value}")
        Text("List size: ${data.list.size}")
    }
}

@Composable
private fun ImmutableComponent(
    data: ImmutableData,
    modifier: Modifier = Modifier
) {
    println("ImmutableComponent recomposed with: ${data.name}")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Immutable: ${data.name}")
        Text("Value: ${data.value}")
        Text("List size: ${data.list.size}")
    }
}
