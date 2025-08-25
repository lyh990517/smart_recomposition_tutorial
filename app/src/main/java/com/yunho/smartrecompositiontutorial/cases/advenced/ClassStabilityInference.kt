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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
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
    val list: List<String>
)

data class UnstableData2(
    val name: String,
    var value: Int,
)

@Stable
data class StableData(
    val name: String,
    val value: Int,
    val list: List<String>
)

data class StableDataWithMutableState(
    val name: String,
    val value: Int,
    val state: MutableState<Int>
)

data class StableDataWithMutableState2(
    val name: String,
    val value: Int,
) {
    var state by mutableIntStateOf(0)
}

data class StableDataWithState(
    val name: String,
    val value: Int,
    val state: State<Int>
)

@Immutable
data class ImmutableData(
    val name: String,
    val value: Int,
    val list: List<String>
)

fun NavGraphBuilder.classStabilityInference() {
    composable<Route.ClassStabilityInference> {
        ClassStabilityInference(
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * Since Compose 2.0, strong skippable is enabled by default, so even unstable classes are skippable.
 * However, in this example, we'll disable strong skippable and explore the stability of the class.
 */
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
            list = listOf("item1", "item2")
        )
    }
    val unstableData2 = remember {
        UnstableData2(
            name = "Unstable",
            value = 42,
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

        UnstableComponent2(
            data = unstableData2,
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
    val stableData1 = remember {
        StableDataWithState(
            name = "Stable",
            value = 42,
            state = mutableIntStateOf(0)
        )
    }
    val stableData2 = remember {
        StableDataWithMutableState(
            name = "Stable",
            value = 42,
            state = mutableIntStateOf(0)
        )
    }
    val stableData3 = remember {
        StableDataWithMutableState2(
            name = "Stable",
            value = 42
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

        StableComponentWithState(
            data = stableData1,
            modifier = Modifier.padding(8.dp)
        )

        StableComponentWithMutableState(
            data = stableData2,
            modifier = Modifier.padding(8.dp)
        )

        StableComponentWithMutableState2(
            data = stableData3,
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
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unstable: ${data.name}")
        Text("Value: ${data.value}")
        Text("List size: ${data.list.size}")
    }
}

@Composable
private fun UnstableComponent2(
    data: UnstableData2,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unstable: ${data.name}")
        Text("Value: ${data.value}")
    }
}

@Composable
private fun StableComponent(
    data: StableData,
    modifier: Modifier = Modifier
) {
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
private fun StableComponentWithState(
    data: StableDataWithState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("StableWithState: ${data.name}")
        Text("Value: ${data.value}")
        Text("State: ${data.state.value}")
    }
}

@Composable
private fun StableComponentWithMutableState(
    data: StableDataWithMutableState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("StableWithMutableState: ${data.name}")
        Text("Value: ${data.value}")
        Text("State: ${data.state.value}")
    }
}

@Composable
private fun StableComponentWithMutableState2(
    data: StableDataWithMutableState2,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("StableWithMutableState2: ${data.name}")
        Text("Value: ${data.value}")
        Text("State: ${data.state}")
    }
}

@Composable
private fun ImmutableComponent(
    data: ImmutableData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Immutable: ${data.name}")
        Text("Value: ${data.value}")
        Text("List size: ${data.list.size}")
    }
}
