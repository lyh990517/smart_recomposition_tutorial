package com.yunho.smartrecompositiontutorial

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    sealed interface Navigable : Route {
        val label: String

        fun NavController.navigate() = navigate(this@Navigable)
    }

    @Serializable
    data object Root : Route {
        val navigableRoutes: List<Navigable> = listOf(
            OptimizedCalculation,
            StateLoop,
            PositionalMemoization,
            DonutHoleSkipping,
            StateDelegation,
            StateDelegationUsingLambda,
            StateCalculationWithDerivedStateOf,
            StateCalculationWithSnapshotFlow,
            PhaseSkipping,
            StateHoisting
        )
    }

    @Serializable
    data object OptimizedCalculation : Navigable {
        override val label: String = "Optimized Calculation"
    }

    @Serializable
    data object StateLoop : Navigable {
        override val label: String = "State Loop"
    }

    @Serializable
    data object PositionalMemoization : Navigable {
        override val label: String = "Positional Memoization"
    }

    @Serializable
    data object DonutHoleSkipping : Navigable {
        override val label: String = "Donut Hole Skipping"
    }

    @Serializable
    data object StateDelegation : Navigable {
        override val label: String = "State Delegation"
    }

    @Serializable
    data object StateDelegationUsingLambda : Navigable {
        override val label: String = "State Delegation Using Lambda"
    }

    @Serializable
    data object StateCalculationWithDerivedStateOf : Navigable {
        override val label: String = "State Calculation With DerivedStateOf"
    }

    @Serializable
    data object StateCalculationWithSnapshotFlow : Navigable {
        override val label: String = "State Calculation With SnapshotFlow"
    }

    @Serializable
    data object PhaseSkipping : Navigable {
        override val label: String = "Phase Skipping"
    }

    @Serializable
    data object StateHoisting : Navigable {
        override val label: String = "State Hoisting"
    }
}
