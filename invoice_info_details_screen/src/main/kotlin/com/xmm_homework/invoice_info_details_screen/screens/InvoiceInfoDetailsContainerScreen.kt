package com.xmm_homework.invoice_info_details_screen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xmm_homework.core.screens.EmptyListScreen
import com.xmm_homework.core.screens.InvoiceInfoMalformedScreen
import com.xmm_homework.core.screens.LoadingIndicator
import com.xmm_homework.core.screens.NetworkErrorScreen
import com.xmm_homework.core.ui.theme.InvoicesAppTheme
import com.xmm_homework.core.view_state.ViewState
import com.xmm_homework.invoice_info_details_screen.models.InvoiceDetailsInputModel
import com.xmm_homework.invoice_info_details_screen.viewmodel.InvoiceInfoDetailsViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun InvoiceInfoDetailsContainerScreen(
    modifier: Modifier,
    invoiceDetailsInputData: InvoiceDetailsInputModel,
) {

    val invoiceInfoDetailsViewModel: InvoiceInfoDetailsViewModel by viewModel()
    val detailsViewState by invoiceInfoDetailsViewModel.container.stateFlow.collectAsState()
    val isRefreshing by invoiceInfoDetailsViewModel.isRefreshing.collectAsState()
    val lazyScrollState = rememberLazyGridState()


    LaunchedEffect(key1 = true) {
        invoiceInfoDetailsViewModel.loadDetailsById(invoiceId = invoiceDetailsInputData.invoiceId)
    }

    Surface(modifier = modifier.background(InvoicesAppTheme.colors.primaryBackground)) {
        when (val viewState = detailsViewState.viewState) {
            is ViewState.Success -> {
                invoiceInfoDetailsViewModel.setRefresh(isRefresh = false)
                SwipeRefresh(modifier = Modifier.layoutId("content"),
                    state = rememberSwipeRefreshState(isRefreshing),
                    onRefresh = { invoiceInfoDetailsViewModel.refresh() },
                    indicator = { state, trigger ->
                        SwipeRefreshIndicator(
                            state = state,
                            refreshTriggerDistance = trigger,
                            scale = true,
                            contentColor = InvoicesAppTheme.colors.buttonPrimary
                        )
                    }
                ) {
                    InvoiceInfoDetailsScreen(
                        modifier = modifier,
                        lazyScrollState = lazyScrollState,
                        data = viewState.data
                    )
                }
            }
            is ViewState.Empty -> {
                invoiceInfoDetailsViewModel.setRefresh(isRefresh = false)
                EmptyListScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(InvoicesAppTheme.colors.primaryBackground)
                ) {
                    invoiceInfoDetailsViewModel.refresh()
                }
            }
            is ViewState.Malformed -> {
                InvoiceInfoMalformedScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(InvoicesAppTheme.colors.primaryBackground)
                ) {
                    invoiceInfoDetailsViewModel.refresh()
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
                    invoiceInfoDetailsViewModel.refresh()
                }
            }
        }
    }
}