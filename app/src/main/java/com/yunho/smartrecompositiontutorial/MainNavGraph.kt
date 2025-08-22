package com.yunho.smartrecompositiontutorial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val LocalNavController = compositionLocalOf<NavController> {
    error("LocalNavController")
}

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController()
) {
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Root
        ) {
            composable<Root> {
                Root()
            }

            composable<Case1> {

            }
        }
    }
}
