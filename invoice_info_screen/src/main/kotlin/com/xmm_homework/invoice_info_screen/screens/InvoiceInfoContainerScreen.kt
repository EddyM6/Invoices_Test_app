package com.xmm_homework.invoice_info_screen.screens

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xmm_homework.core.navigation.AppRoutes
import com.xmm_homework.core.screens.EmptyListScreen
import com.xmm_homework.core.screens.InvoiceInfoMalformedScreen
import com.xmm_homework.core.screens.LoadingIndicator
import com.xmm_homework.core.screens.NetworkErrorScreen
import com.xmm_homework.core.ui.theme.InvoicesAppTheme
import com.xmm_homework.core.view_state.ViewState
import com.xmm_homework.invoice_info_screen.models.InvoiceInfoScreenSideEffect
import com.xmm_homework.invoice_info_screen.viewmodels.InvoiceInfoViewModel
import org.koin.androidx.compose.viewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun InvoiceInfoScreen(
    modifier: Modifier,
    navController: NavController
) {
    val invoiceInfoViewModel: InvoiceInfoViewModel by viewModel()
    val invoiceInfoViewState by invoiceInfoViewModel.container.stateFlow.collectAsState()
    val isRefreshing by invoiceInfoViewModel.isRefreshing.collectAsState()
    val systemUiController = rememberSystemUiController()
    val isDarkModeValue = isSystemInDarkTheme()
    val isDarkMode by remember { mutableStateOf(isDarkModeValue) }
    val lazyScrollState = rememberLazyGridState()
    val activity = LocalContext.current as Activity
    val statusBackground = InvoicesAppTheme.colors.primaryBackground.value

    SideEffect {
        WindowCompat.setDecorFitsSystemWindows(activity.window, true)
        // Change status bar color
        systemUiController.setStatusBarColor(
            color = Color(statusBackground),
            darkIcons = !isDarkMode
        )
    }
    LaunchedEffect(key1 = true) {
        invoiceInfoViewModel.loadData()
    }
    invoiceInfoViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is InvoiceInfoScreenSideEffect.NavigateToInvoiceDetailsScreen -> {
                if (sideEffect.isItemClicked) {
                    navController.navigate(
                        "${AppRoutes.InvoiceInfoDetailsScreen.route}/${
                            Uri.encode(
                                sideEffect.data.invoiceId
                            )
                        }"
                    )
                }
            }
        }
    }

    Surface(modifier = modifier.background(InvoicesAppTheme.colors.primaryBackground)) {
        when (val viewState = invoiceInfoViewState.viewState) {
            is ViewState.Success -> {
                invoiceInfoViewModel.setRefresh(isRefresh = false)
                SwipeRefresh(modifier = Modifier.layoutId("content"),
                    state = rememberSwipeRefreshState(isRefreshing),
                    onRefresh = { invoiceInfoViewModel.refresh() },
                    indicator = { state, trigger ->
                        SwipeRefreshIndicator(
                            state = state,
                            refreshTriggerDistance = trigger,
                            scale = true,
                            contentColor = InvoicesAppTheme.colors.buttonPrimary
                        )
                    }
                ) {
                    InvoiceInfoSuccessScreen(
                        modifier = modifier,
                        lazyScrollState = lazyScrollState,
                        data = viewState.data.items
                    ) {
                        invoiceInfoViewModel.itemClicked(data = it)
                    }
                }
            }
            is ViewState.Empty -> {
                invoiceInfoViewModel.setRefresh(isRefresh = false)
                EmptyListScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(InvoicesAppTheme.colors.primaryBackground)
                ) {
                    invoiceInfoViewModel.refresh()
                }
            }
            is ViewState.Malformed -> {
                InvoiceInfoMalformedScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(InvoicesAppTheme.colors.primaryBackground)
                ) {
                    invoiceInfoViewModel.refresh()
                }
            }
            is ViewState.Loading -> {
                LoadingIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(InvoicesAppTheme.colors.primaryBackground)
                )

            }
            is ViewState.Error -> {
                NetworkErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(InvoicesAppTheme.colors.primaryBackground)
                ) {
                    invoiceInfoViewModel.refresh()
                }
            }
        }
    }
}


