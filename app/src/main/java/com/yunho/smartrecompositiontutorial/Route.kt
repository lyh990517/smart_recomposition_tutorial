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
            CalculationCachingWithRemember,
            AvoidingBackwardsWrites,
            PositionalMemoization,
            DonutHoleSkipping,
            StatePassing,
            DeferReadsAsLongAsPossible,
            StateCalculationWithDerivedStateOf,
            StateCalculationWithSnapshotFlow,
            PhaseSkipping,
            StateHoisting,
            ClassStabilityInference,
            ContentType
        )
    }

    @Serializable
    data object CalculationCachingWithRemember : Navigable {
        override val label: String = "Calculation Caching With Remember"
    }

    @Serializable
    data object AvoidingBackwardsWrites : Navigable {
        override val label: String = "Avoiding Backwards Writes"
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
    data object StatePassing : Navigable {
        override val label: String = "State Passing"
    }

    @Serializable
    data object DeferReadsAsLongAsPossible : Navigable {
        override val label: String = "Defer Reads As Long As Possible"
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

    @Serializable
    data object ClassStabilityInference : Navigable {
        override val label: String = "Class Stability Inference"
    }

    @Serializable
    data object ContentType : Navigable {
        override val label: String = "Content Type"
    }
}
