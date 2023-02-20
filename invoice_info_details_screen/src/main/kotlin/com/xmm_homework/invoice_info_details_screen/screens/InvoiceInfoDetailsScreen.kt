package com.xmm_homework.invoice_info_details_screen.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.xmm_homework.core.R
import com.xmm_homework.core.ui.theme.InvoicesAppExerciseTheme
import com.xmm_homework.core.ui.theme.InvoicesAppTheme
import com.xmm_homework.invoice_info_details_screen.models.InvoiceDetailsUiModel

private const val GRID_CELLS_FIX_COUNT = 1

@Composable
internal fun InvoiceInfoDetailsScreen(
    modifier: Modifier,
    lazyScrollState: LazyGridState,
    data: List<InvoiceDetailsUiModel>,
) {
    // invoice info recourses
    val itemHorizontalDivider = dimensionResource(id = R.dimen.invoice_info_item_divider_hor)
    val itemVerticalDivider = dimensionResource(id = R.dimen.invoice_info_item_divider_vertical)
    val itemContentHorizontalPadding = dimensionResource(id = R.dimen.invoice_info_item_padding_hor)
    val itemContentVerticalPadding =
        dimensionResource(id = R.dimen.invoice_info_item_padding_vertical)

    LazyVerticalGrid(
        modifier = modifier.background(InvoicesAppTheme.colors.primaryBackground),
        verticalArrangement = Arrangement.spacedBy(itemVerticalDivider),
        horizontalArrangement = Arrangement.spacedBy(
            itemHorizontalDivider
        ),
        contentPadding = PaddingValues(
            horizontal = itemContentHorizontalPadding,
            vertical = itemContentVerticalPadding
        ),
        columns = GridCells.Fixed(GRID_CELLS_FIX_COUNT),
        state = lazyScrollState

    ) {
        items(items = data, key = { it.id }) { item ->
            InvoiceInfoDetailsItemScreen(
                modifier = modifier,
                data = item
            )
        }
    }
}

@Preview(
    name = "Invoice Info Details Screen Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun InvoiceInfoDetailsSuccessScreenPreview() {
    InvoicesAppExerciseTheme(darkTheme = false) {
        InvoiceInfoDetailsScreen(
            modifier = Modifier.fillMaxSize(),
            lazyScrollState = rememberLazyGridState(),
            data = InvoiceDetailsUiModel.previewData()
        )
    }
}

@Preview(
    name = "Invoice Info Details Screen Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun InvoiceInfoDetailsScreenDarkModePreview() {
    InvoicesAppExerciseTheme(darkTheme = true) {
        InvoiceInfoDetailsScreen(
            modifier = Modifier.fillMaxSize(),
            lazyScrollState = rememberLazyGridState(),
            data = InvoiceDetailsUiModel.previewData()
        )
    }
}