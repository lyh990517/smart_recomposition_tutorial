package com.yunho.smartrecompositiontutorial

import kotlinx.serialization.Serializable

@Serializable
sealed interface MainRoute

@Serializable
data object Root : MainRoute

@Serializable
data object OptimizedCalculation : MainRoute

@Serializable
data object StateLoop : MainRoute

@Serializable
data object PositionalMemoization : MainRoute
