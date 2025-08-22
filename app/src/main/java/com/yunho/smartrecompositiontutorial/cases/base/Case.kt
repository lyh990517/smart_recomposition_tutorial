package com.yunho.smartrecompositiontutorial.cases.base

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

    companion object {
        private const val PROBLEM = "Problem"
        private const val SOLUTION = "Solution"
    }
}
