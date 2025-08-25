package com.yunho.smartrecompositiontutorial.cases.advenced

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yunho.smartrecompositiontutorial.Route
import com.yunho.smartrecompositiontutorial.base.Case
import com.yunho.smartrecompositiontutorial.base.Tutorial

private sealed interface ListItem {
    val id: String

    data class Header(
        override val id: String,
        val title: String
    ) : ListItem

    data class Content(
        override val id: String,
        val text: String
    ) : ListItem

    data class Footer(
        override val id: String,
        val note: String
    ) : ListItem
}

fun NavGraphBuilder.contentType() {
    composable<Route.ContentType> {
        ContentType(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ContentType(
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
    val items = remember {
        listOf(
            ListItem.Header("h-1", "Section 1"),
            ListItem.Header("h-2", "Section 2"),
            ListItem.Header("h-3", "Section 3"),
            ListItem.Header("h-4", "Section 4"),
            ListItem.Header("h-5", "Section 5"),
            ListItem.Content("c-1", "Item 1"),
            ListItem.Content("c-2", "Item 2"),
            ListItem.Content("c-3", "Item 3"),
            ListItem.Content("c-4", "Item 4"),
            ListItem.Content("c-5", "Item 5"),
            ListItem.Footer("f-1", "Footer 1"),
            ListItem.Footer("f-2", "Footer 2"),
            ListItem.Footer("f-3", "Footer 3"),
            ListItem.Footer("f-4", "Footer 4"),
            ListItem.Footer("f-5", "Footer 5")
        ).shuffled()
    }

    Column(modifier = modifier) {
        Text(
            text = "Problem: LazyColumn without contentType - inefficient item reuse",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        // LazyColumn without contentType - less efficient item composition reuse
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(60.dp)
        ) {
            items(items = items) { item ->
                when (item) {
                    is ListItem.Header -> HeaderItem(title = item.title)
                    is ListItem.Content -> ContentItem(text = item.text)
                    is ListItem.Footer -> FooterItem(note = item.note)
                }
            }
        }
    }
}

@Composable
private fun Solution(
    modifier: Modifier = Modifier
) {
    val items = remember {
        listOf(
            ListItem.Header("h-1", "Section 1"),
            ListItem.Header("h-2", "Section 2"),
            ListItem.Header("h-3", "Section 3"),
            ListItem.Header("h-4", "Section 4"),
            ListItem.Header("h-5", "Section 5"),
            ListItem.Content("c-1", "Item 1"),
            ListItem.Content("c-2", "Item 2"),
            ListItem.Content("c-3", "Item 3"),
            ListItem.Content("c-4", "Item 4"),
            ListItem.Content("c-5", "Item 5"),
            ListItem.Footer("f-1", "Footer 1"),
            ListItem.Footer("f-2", "Footer 2"),
            ListItem.Footer("f-3", "Footer 3"),
            ListItem.Footer("f-4", "Footer 4"),
            ListItem.Footer("f-5", "Footer 5")
        ).shuffled()
    }

    Column(modifier = modifier) {
        Text(
            text = "Solution: LazyColumn with contentType - efficient item reuse",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        // LazyColumn with contentType - more efficient item composition reuse
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(60.dp)
        ) {
            items(
                items = items,
                key = { item -> item.id },
                contentType = { item -> item::class }
            ) { item ->
                when (item) {
                    is ListItem.Header -> HeaderItem(title = item.title)
                    is ListItem.Content -> ContentItem(text = item.text)
                    is ListItem.Footer -> FooterItem(note = item.note)
                }
            }
        }
    }
}

@Composable
private fun HeaderItem(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun ContentItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.padding(start = 16.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun FooterItem(
    note: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = note,
        style = MaterialTheme.typography.bodySmall,
        color = Color.Gray
    )
}
