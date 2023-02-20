package com.xmm_homework.invoices.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.xmm_homework.core.navigation.AppRoutes
import com.xmm_homework.invoice_info_details_screen.models.InvoiceDetailsInputModel
import com.xmm_homework.invoice_info_details_screen.screens.InvoiceInfoDetailsContainerScreen
import com.xmm_homework.invoice_info_screen.screens.InvoiceInfoScreen
import com.xmm_homework.splash_screen.ui.AnimatedSplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppRoutes.SplashScreen.route
    ) {
        composable(route = AppRoutes.SplashScreen.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = AppRoutes.InvoiceInfoScreen.route) {
            InvoiceInfoScreen(modifier = Modifier.fillMaxSize(), navController = navController)
        }
        composable(
            route = "${AppRoutes.InvoiceInfoDetailsScreen.route}/{data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                slideInHorizontally(animationSpec = tween(500)) { 1100 }
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(500)) { -1100 }
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(500)) { -1100 }
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(500)) { 1100 }
            }
        ) {
            val invoiceId = it.arguments?.getString("data")
            invoiceId?.let {
                if (invoiceId.isNotBlank()) {
                    InvoiceInfoDetailsContainerScreen(
                        modifier = Modifier
                            .fillMaxSize(),
                        invoiceDetailsInputData = InvoiceDetailsInputModel(invoiceId = invoiceId)
                    )
                }
            }
        }
    }
}