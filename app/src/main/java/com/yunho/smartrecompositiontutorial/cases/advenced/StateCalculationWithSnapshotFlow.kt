package com.yunho.smartrecompositiontutorial.cases.advenced

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yunho.smartrecompositiontutorial.Route
import com.yunho.smartrecompositiontutorial.base.Case
import com.yunho.smartrecompositiontutorial.base.Tutorial
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

fun NavGraphBuilder.stateCalculationWithSnapshotFlow() {
    composable<Route.StateCalculationWithSnapshotFlow> {
        StateCalculationWithSnapshotFlow(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun StateCalculationWithSnapshotFlow(
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
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val showTopBar = lazyListState.firstVisibleItemIndex > 20

    LaunchedEffect(showTopBar) {
        if (showTopBar) {
            Toast.makeText(context, "showTopBar is true", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = modifier) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(100) {
                Text("visible item index: $it")
            }
        }

        AnimatedVisibility(
            visible = showTopBar,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Magenta),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Top Bar")
            }
        }
    }
}

@Composable
private fun Solution(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    var showTopBar by remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .map { it > 20 }
            .distinctUntilChanged()
            .collect { visible ->
                showTopBar = visible

                if (visible) {
                    Toast.makeText(context, "showTopBar is true", Toast.LENGTH_SHORT).show()
                }
            }
    }

    Box(modifier = modifier) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(100) {
                Text("visible item index: $it")
            }
        }

        AnimatedVisibility(
            visible = showTopBar,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Magenta),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Top Bar")
            }
        }
    }
}
