package com.yunho.smartrecompositiontutorial

import kotlinx.serialization.Serializable

@Serializable
sealed interface MainRoute

@Serializable
data object Root : MainRoute

@Serializable
data object Case1 : MainRoute

