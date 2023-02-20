package com.xmm_homework.invoice_info_screen.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.xmm_homework.core.ui.theme.InvoicesAppTheme
import com.xmm_homework.core.R
import com.xmm_homework.core.ui.theme.InvoicesAppExerciseTheme
import com.xmm_homework.invoice_info_screen.models.ItemUiModel

private const val GRID_CELLS_FIX_COUNT = 1

@Composable
internal fun InvoiceInfoSuccessScreen(
    modifier: Modifier,
    lazyScrollState: LazyGridState,
    data: List<ItemUiModel>,
    itemClicked: (ItemUiModel) -> Unit
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
            InvoiceItemSuccessItem(
                modifier = modifier,
                data = item
            ) { onItemClickData ->
                itemClicked.invoke(onItemClickData)
            }
        }
    }
}

@Preview(
    name = "Invoice Info Screen Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun InvoiceInfoSuccessScreenPreview() {
    InvoicesAppExerciseTheme(darkTheme = false) {
        InvoiceInfoSuccessScreen(
            modifier = Modifier.fillMaxSize(),
            lazyScrollState = rememberLazyGridState(),
            data = ItemUiModel.previewData(),
        ) {
            // item clicked
        }
    }
}

@Preview(
    name = "Invoice Info Screen Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun InvoiceInfoSuccessScreenDarkModePreview() {
    InvoicesAppExerciseTheme(darkTheme = true) {
        InvoiceInfoSuccessScreen(
            modifier = Modifier.fillMaxSize(),
            lazyScrollState = rememberLazyGridState(),
            data = ItemUiModel.previewData(),
        ) {
            // item clicked
        }
    }
}