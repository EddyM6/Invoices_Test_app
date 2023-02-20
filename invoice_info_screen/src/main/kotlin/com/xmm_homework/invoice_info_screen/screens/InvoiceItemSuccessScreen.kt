package com.xmm_homework.invoice_info_screen.screens

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
import com.xmm_homework.core.extensions.clickableSingle
import com.xmm_homework.core.ui.theme.InvoicesAppExerciseTheme
import com.xmm_homework.core.ui.theme.InvoicesAppTheme
import com.xmm_homework.invoice_info_screen.models.ItemUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InvoiceItemSuccessItem(
    modifier: Modifier,
    data: ItemUiModel,
    onItemClicked: (ItemUiModel) -> Unit
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
            .clickableSingle {
                onItemClicked.invoke(data)
            }
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
                    .height(20.dp)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = firstLabelStartPadding, end = firstLabelEndPadding),
                style = InvoicesAppTheme.typography.labelMedium,
                color = InvoicesAppTheme.colors.primaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = data.date
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = firstLabelStartPadding, end = firstLabelEndPadding),
                style = InvoicesAppTheme.typography.labelMedium,
                color = InvoicesAppTheme.colors.primaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = data.totalDetails
            )
        }
    }
}

@Composable
@Preview(
    name = "ItemAlbumInfoLightMode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun ItemInvoiceInfoScreenLightModePreview() {
    InvoicesAppExerciseTheme(darkTheme = false) {
        InvoiceItemSuccessItem(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            data = ItemUiModel.previewData().first()
        ) {
            // item clicked
        }
    }
}


@Composable
@Preview(
    name = "ItemAlbumInfoDarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
private fun ItemInvoiceInfoScreenDarkModePreview() {
    InvoicesAppExerciseTheme(darkTheme = true) {
        InvoiceItemSuccessItem(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            data = ItemUiModel.previewData().first()
        ) {
            // item clicked
        }
    }
}

