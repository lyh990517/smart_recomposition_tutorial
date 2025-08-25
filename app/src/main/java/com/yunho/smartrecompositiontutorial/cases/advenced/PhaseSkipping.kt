package com.yunho.smartrecompositiontutorial.cases.advenced

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yunho.smartrecompositiontutorial.Route
import com.yunho.smartrecompositiontutorial.base.Case
import com.yunho.smartrecompositiontutorial.base.Tutorial

fun NavGraphBuilder.phaseSkipping() {
    composable<Route.PhaseSkipping> {
        PhaseSkipping(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun PhaseSkipping(
    modifier: Modifier = Modifier
) {
    Tutorial(modifier = modifier) { case ->
        when (case) {
            Case.Problem -> Problem(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Case.Solution -> Solution(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun Problem(
    modifier: Modifier = Modifier
) {
    var animate by remember { mutableStateOf(false) }
    val animatedValue by animateFloatAsState(if (animate) 1f else 0f)

    Box(
        modifier = modifier,
    ) {
        Button(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = { animate = !animate }
        ) {
            Text(text = "Animate")
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 60.dp * animatedValue)
                .alpha(animatedValue),
            text = "Animate"
        )
    }
}

@Composable
private fun Solution(
    modifier: Modifier = Modifier
) {
    var animate by remember { mutableStateOf(false) }

    Box(
        modifier = modifier,
    ) {
        Button(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = { animate = !animate }
        ) {
            Text(text = "Animate")
        }

        AnimatedText(
            animate = { animate }, // pass state accessor (actual read happens inside AnimatedText)
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun AnimatedText(
    animate: () -> Boolean,
    modifier: Modifier = Modifier
) {
    // Make sure to keep it only where you use the state.
    val animatedValue by animateFloatAsState(if (animate()) 1f else 0f)

    Row(
        modifier = modifier.offset { IntOffset(0, (60 * animatedValue).toInt()) } // read state on layout phase
    ) {
        Text(
            modifier = modifier
                .drawWithContent { // read state on draw phase
                    scale(scale = animatedValue) {
                        this@drawWithContent.drawContent()
                    }
                },
            text = "Animate"
        )

        Text(
            modifier = modifier
                .graphicsLayer { // set config on composition phase, use config on draw phase
                    scaleX = animatedValue
                    scaleY = animatedValue
                },
            text = "Animate"
        )
    }
}
