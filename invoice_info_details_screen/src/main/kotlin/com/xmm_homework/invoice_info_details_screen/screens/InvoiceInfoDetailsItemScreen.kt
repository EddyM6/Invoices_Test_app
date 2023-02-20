package com.xmm_homework.invoice_info_details_screen.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xmm_homework.core.R
import com.xmm_homework.core.ui.theme.InvoicesAppExerciseTheme
import com.xmm_homework.core.ui.theme.InvoicesAppTheme
import com.xmm_homework.invoice_info_details_screen.models.InvoiceDetailsUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InvoiceInfoDetailsItemScreen(
    modifier: Modifier,
    data: InvoiceDetailsUiModel,
) {
    // recourses
    val cardElevation =
        dimensionResource(id = R.dimen.invoice_info_item_card_elevation)
    val firstLabelStartPadding =
        dimensionResource(id = R.dimen.invoice_info_item_first_label_padding_start)
    val firstLabelEndPadding =
        dimensionResource(id = R.dimen.invoice_info_item_first_label_padding_end)
    val itemHeight = dimensionResource(id = R.dimen.invoice_info_item_height)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(itemHeight)
            .shadow(
                clip = true,
                ambientColor = Color.Green,
                elevation = cardElevation,
                shape = InvoicesAppTheme.roundedCornerShape.shapeSmall
            )
            .background(InvoicesAppTheme.colors.secondaryBackground),
        shape = InvoicesAppTheme.roundedCornerShape.shapeSmall,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(InvoicesAppTheme.colors.secondaryBackground),

            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = firstLabelStartPadding, end = firstLabelEndPadding),
                style = InvoicesAppTheme.typography.labelMedium,
                color = InvoicesAppTheme.colors.primaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = data.id
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = firstLabelStartPadding, end = firstLabelEndPadding),
                style = InvoicesAppTheme.typography.labelMedium,
                color = InvoicesAppTheme.colors.primaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = data.name
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = firstLabelStartPadding, end = firstLabelEndPadding),
                style = InvoicesAppTheme.typography.labelMedium,
                color = InvoicesAppTheme.colors.primaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = data.priceInCents
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = firstLabelStartPadding, end = firstLabelEndPadding),
                style = InvoicesAppTheme.typography.labelMedium,
                color = InvoicesAppTheme.colors.primaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = data.quantity
            )
        }
    }
}

@Preview(
    name = "ItemDetailsScreenLightMode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ItemInvoiceDetailsScreenLightModePreview() {
    InvoicesAppExerciseTheme(darkTheme = false) {
        InvoiceInfoDetailsItemScreen(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            data = InvoiceDetailsUiModel.previewData().first()
        )
    }
}

@Preview(
    name = "ItemDetailsScreenDarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ItemInvoiceDetailsScreenDarkModePreview() {
    InvoicesAppExerciseTheme(darkTheme = true) {
        InvoiceInfoDetailsItemScreen(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            data = InvoiceDetailsUiModel.previewData().first()
        )
    }
}