package com.yunho.smartrecompositiontutorial.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class CaseState(initialCase: Case) {
    var value by mutableStateOf(initialCase)
    val buttonText: String get() = value.toggled.label

    fun toggle() {
        value = value.toggled
    }

    companion object {

        @Composable
        fun rememberCaseState(initialCase: Case = Case.Problem) = remember { CaseState(initialCase) }
    }
}
