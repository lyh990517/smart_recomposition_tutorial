package com.yunho.smartrecompositiontutorial.cases

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class Item(
    val id: Int,
    val name: String
)

@Composable
fun PositionalMemoization(
    modifier: Modifier = Modifier
) {
    val items = remember {
        mutableStateListOf<Item>().apply {
            addAll((1..5).map { Item(id = it, name = "Item $it") })
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {
                val newId = (items.maxOfOrNull { it.id } ?: 0) + 1

                items.add(
                    index = 3,
                    element = Item(id = newId, name = "New Item $newId")
                )
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Insert Item at Position 3")
        }

        Button(
            onClick = {
                val newId = (items.maxOfOrNull { it.id } ?: 0) + 1

                items.add(element = Item(id = newId, name = "New Item $newId"))
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Insert Item at Last")
        }

        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(
                items = items
            ) { index, item ->
                Text(
                    text = "${item.name} (ID: ${item.id}, Index: $index)",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun PositionalMemoizationSolution(
    modifier: Modifier = Modifier
) {
    val items = remember {
        mutableStateListOf<Item>().apply {
            addAll((1..5).map { Item(id = it, name = "Item $it") })
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {
                val newId = (items.maxOfOrNull { it.id } ?: 0) + 1

                items.add(
                    index = 3,
                    element = Item(id = newId, name = "New Item $newId")
                )
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Insert Item at Position 3")
        }

        Button(
            onClick = {
                val newId = (items.maxOfOrNull { it.id } ?: 0) + 1

                items.add(element = Item(id = newId, name = "New Item $newId"))
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Insert Item at Last")
        }

        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(
                items = items,
                key = { _, item -> item.id },
            ) { index, item ->
                Text(
                    text = "${item.name} (ID: ${item.id}, Index: $index)",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

