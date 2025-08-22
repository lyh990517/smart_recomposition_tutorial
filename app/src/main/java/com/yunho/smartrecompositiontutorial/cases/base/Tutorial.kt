package com.yunho.smartrecompositiontutorial.cases.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yunho.smartrecompositiontutorial.cases.base.CaseState.Companion.rememberCaseState

@Composable
fun Tutorial(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(state: Case) -> Unit = {}
) {
    val caseState = rememberCaseState()

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = caseState::toggle
            ) {
                Text(text = caseState.buttonText)
            }
        }

        content(caseState.value)
    }
}
