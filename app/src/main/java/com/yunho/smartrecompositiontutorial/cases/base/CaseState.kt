package com.yunho.smartrecompositiontutorial.cases.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class CaseState(initialCase: Case) {
    var value by mutableStateOf(initialCase)
    val buttonText: String get() = value.buttonText

    fun toggle() {
        value = value.toggled
    }

    sealed interface Case {
        val buttonText: String
        val toggled: Case

        data object Problem : Case {
            override val buttonText: String = "Solution"
            override val toggled: Case = Solution
        }

        data object Solution : Case {
            override val buttonText: String = "Problem"
            override val toggled: Case = Problem
        }
    }

    companion object {
        @Composable
        fun rememberCaseState(initialCase: Case = Case.Problem) = remember { mutableStateOf(CaseState(initialCase)) }
    }
}
