package com.xmm_homework.core.navigation

sealed class AppRoutes(val route: String) {
    object SplashScreen : AppRoutes(route = "Splash Screen")
    object InvoiceInfoScreen : AppRoutes(route = "Invoice Info Screen")
    object InvoiceInfoDetailsScreen : AppRoutes(route = "Invoice Info Details Screen")
}
