package com.yunho.smartrecompositiontutorial.cases.advenced

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.yunho.data.DataInterface
import com.yunho.data.DataModel
import com.yunho.smartrecompositiontutorial.Route
import com.yunho.smartrecompositiontutorial.base.Tutorial
import com.yunho.smartrecompositiontutorial.base.Case.Problem as UnstableCase
import com.yunho.smartrecompositiontutorial.base.Case.Solution as StableCase

data class UnstableData1(
    val name: String, // stable
    val value: Int, // stable
    val list: List<String> // unstable
)

data class UnstableData2(
    val name: String, // stable
    var value: Int, // stable
)

interface UnstableData3 {
    val name: String // stable
    var value: Int // stable
}

@Stable
data class StableData1(
    val name: String, // stable
    val value: Int, // stable
    val list: List<String> // unstable
)

data class StableData2(
    val name: String, // stable
    val value: Int, // stable
    val state: MutableState<Int> // stable
)

data class StableData3(
    val name: String, // stable
    val value: Int, // stable
) {
    var state by mutableIntStateOf(0) // stable
}

data class StableData4(
    val name: String, // stable
    val value: Int, // stable
    val state: State<Int> // stable
)

@Stable
data class StableData5(
    val name: String, // stable
    var value: Int, // stable
)

interface StableData6 {
    val name: String // stable
    val value: Int // stable
}

// stable class
data class PresentationModel(
    val name: String, // stable
    val value: Int // stable
) {
    constructor(dataModel: DataModel) : this(
        name = dataModel.name + " mapped to presentation model",
        value = dataModel.value
    )
}

@Immutable
data class ImmutableData1(
    val name: String, // stable
    val value: Int, // stable
    val list: List<String> // unstable
)

@Immutable
data class ImmutableData2(
    val name: String, // stable
    var value: Int, // stable
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
            UnstableCase -> Unstable(
                modifier = Modifier.fillMaxWidth()
            )

            StableCase -> Stable(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Unstable(
    modifier: Modifier = Modifier
) {
    var counter by remember { mutableIntStateOf(0) }
    val unstableData = remember {
        UnstableData1(
            name = "UnstableData1",
            value = 42,
            list = listOf("item1", "item2")
        )
    }
    val unstableData2 = remember {
        UnstableData2(
            name = "UnstableData2",
            value = 42,
        )
    }
    val unstableData3 = remember {
        object : UnstableData3 {
            override val name: String = "UnstableData3"
            override var value: Int = 42
        }
    }
    val dataModel = remember {
        DataModel(
            name = "DataModel",
            value = 42,
        )
    }
    val dataInterface = remember {
        object : DataInterface {
            override val name: String = "DataInterface"
            override val value: Int = 42
        }
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
        Unstable1(
            data = unstableData,
            modifier = Modifier.padding(8.dp)
        )

        Unstable2(
            data = unstableData2,
            modifier = Modifier.padding(8.dp)
        )

        Unstable3(
            data = unstableData3,
            modifier = Modifier.padding(8.dp)
        )

        DataModel(
            data = dataModel,
            modifier = Modifier.padding(8.dp)
        )

        DataInterface(
            data = dataInterface,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun Stable(
    modifier: Modifier = Modifier
) {
    var counter by remember { mutableIntStateOf(0) }
    val stableData1 = remember {
        StableData1(
            name = "StableData1",
            value = 42,
            list = listOf("item1", "item2")
        )
    }
    val stableData2 = remember {
        StableData2(
            name = "StableData2",
            value = 42,
            state = mutableIntStateOf(0)
        )
    }
    val stableData3 = remember {
        StableData3(
            name = "StableData3",
            value = 42
        )
    }
    val stableData4 = remember {
        StableData4(
            name = "StableData4",
            value = 42,
            state = mutableIntStateOf(0)
        )
    }
    val stableData5 = remember {
        StableData5(
            name = "StableData5",
            value = 42
        )
    }
    val stableData6 = remember {
        object : StableData6 {
            override val name: String = "StableData6"
            override val value: Int = 42
        }
    }
    val immutableData1 = remember {
        ImmutableData1(
            name = "ImmutableData1",
            value = 42,
            list = listOf("item1", "item2")
        )
    }
    val immutableData2 = remember {
        ImmutableData2(
            name = "ImmutableData2",
            value = 42,
        )
    }
    val presentationModel = remember {
        PresentationModel(
            dataModel = DataModel(
                name = "DataModel",
                value = 42
            )
        )
    }

    Column(
        modifier = modifier.verticalScroll(state = rememberScrollState()),
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
        Stable1(
            data = stableData1,
            modifier = Modifier.padding(8.dp)
        )

        Stable2(
            data = stableData2,
            modifier = Modifier.padding(8.dp)
        )

        Stable3(
            data = stableData3,
            modifier = Modifier.padding(8.dp)
        )

        Stable4(
            data = stableData4,
            modifier = Modifier.padding(8.dp)
        )

        Stable5(
            data = stableData5,
            modifier = Modifier.padding(8.dp)
        )

        Stable6(
            data = stableData6,
            modifier = Modifier.padding(8.dp)
        )

        Immutable1(
            data = immutableData1,
            modifier = Modifier.padding(8.dp)
        )

        Immutable2(
            data = immutableData2,
            modifier = Modifier.padding(8.dp)
        )

        PresentationModel(
            data = presentationModel,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun Unstable1(
    data: UnstableData1,
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
private fun Unstable2(
    data: UnstableData2,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unstable: ${data.name}")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Value: ${data.value}")

            Button(
                onClick = {
                    data.value += 2
                    println("value changed: ${data.value}")
                }
            ) {
                Text(text = "change value")
            }
        }
    }
}

@Composable
private fun Unstable3(
    data: UnstableData3,
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
private fun DataModel(
    data: DataModel,
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
private fun DataInterface(
    data: DataInterface,
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
private fun Stable1(
    data: StableData1,
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
private fun Stable2(
    data: StableData2,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Stable: ${data.name}")
        Text("Value: ${data.value}")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("State: ${data.state.value}")

            Button(
                onClick = {
                    data.state.value += 2
                    println("value changed: ${data.state.value}")
                }
            ) {
                Text(text = "change value")
            }
        }
    }
}

@Composable
private fun Stable3(
    data: StableData3,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Stable: ${data.name}")
        Text("Value: ${data.value}")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("State: ${data.state}")

            Button(
                onClick = {
                    data.state += 2
                    println("value changed: ${data.state}")
                }
            ) {
                Text(text = "change value")
            }
        }
    }
}

@Composable
private fun Stable4(
    data: StableData4,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Stable: ${data.name}")
        Text("Value: ${data.value}")
        Text("State: ${data.state.value}")
    }
}

@Composable
private fun Stable5(
    data: StableData5,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Stable: ${data.name}")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Value: ${data.value}")

            Button(
                onClick = {
                    data.value += 2
                    println("value changed: ${data.value}")
                }
            ) {
                Text(text = "change value")
            }
        }
    }
}

@Composable
private fun Stable6(
    data: StableData6,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Stable: ${data.name}")
        Text("Value: ${data.value}")
    }
}

@Composable
private fun Immutable1(
    data: ImmutableData1,
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

@Composable
private fun Immutable2(
    data: ImmutableData2,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Immutable: ${data.name}")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Value: ${data.value}")

            Button(
                onClick = {
                    data.value += 2
                    println("value changed: ${data.value}")
                }
            ) {
                Text(text = "change value")
            }
        }
    }
}

@Composable
private fun PresentationModel(
    data: PresentationModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Stable: ${data.name}")
        Text("Value: ${data.value}")
    }
}
