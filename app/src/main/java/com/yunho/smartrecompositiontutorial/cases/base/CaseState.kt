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
    val buttonText: String get() = value.toggled.label

    fun toggle() {
        value = value.toggled
    }

    sealed interface Case {
        val label: String
        val toggled: Case

        data object Problem : Case {
            override val label: String = PROBLEM
            override val toggled: Case = Solution
        }

        data object Solution : Case {
            override val label: String = SOLUTION
            override val toggled: Case = Problem
        }
    }

    companion object {
        private const val PROBLEM = "Problem"
        private const val SOLUTION = "Solution"

        @Composable
        fun rememberCaseState(initialCase: Case = Case.Problem) = remember { CaseState(initialCase) }
    }
}
