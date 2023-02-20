package com.xmm_homework.invoices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xmm_homework.core.ui.theme.InvoicesAppExerciseTheme
import com.xmm_homework.core.ui.theme.InvoicesAppStyle
import com.xmm_homework.invoices.nav_graph.AppNavGraph

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkModeValue = isSystemInDarkTheme()
            val currentStyle by remember { mutableStateOf(InvoicesAppStyle.Main) }
            val isDarkMode by remember { mutableStateOf(isDarkModeValue) }
            val systemUiController = rememberSystemUiController()

            if (isDarkMode) {
                systemUiController.setSystemBarsColor(color = Color.Transparent)
            } else {
                systemUiController.setSystemBarsColor(color = Color.White)
            }

            InvoicesAppExerciseTheme(
                darkTheme = isDarkMode,
                style = currentStyle
            ) {
                CompositionLocalProvider {
                    val animatedNavController = rememberAnimatedNavController()
                    AppNavGraph(
                        navController = animatedNavController
                    )
                }
            }
        }
    }
}


