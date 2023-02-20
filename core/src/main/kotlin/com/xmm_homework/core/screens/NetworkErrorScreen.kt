package com.xmm_homework.core.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.*
import com.xmm_homework.core.R
import com.xmm_homework.core.ui.theme.InvoicesAppExerciseTheme
import com.xmm_homework.core.ui.theme.InvoicesAppTheme

@Composable
fun NetworkErrorScreen(
    modifier: Modifier,
    tryAgainButtonClicked: () -> Unit
) {
    // recourses
    val networkErrorTitle = stringResource(id = R.string.network_error_title)
    val networkErrorDescription = stringResource(id = R.string.network_error_description)
    val tryAgainButtonText = stringResource(id = R.string.network_error_try_again_button_text)

    val errIconHeight = dimensionResource(id = R.dimen.network_error_anim_icon_height)
    val errIconMarginTop = dimensionResource(id = R.dimen.network_error_anim_icon_margin_top)
    val titleMarginTop = dimensionResource(id = R.dimen.network_error_title_margin_top)
    val descriptionMarginHor = dimensionResource(id = R.dimen.network_error_description_margin_hor)
    val descriptionMarginTop = dimensionResource(id = R.dimen.network_error_description_margin_top)
    val tryAgainButtonMarginBottom =
        dimensionResource(id = R.dimen.network_error_try_again_button_margin_bottom)
    val tryAgainButtonContentPaddingHor =
        dimensionResource(id = R.dimen.network_error_try_again_button_content_padding_hor)
    val tryAgainButtonContentPaddingVertical =
        dimensionResource(id = R.dimen.network_error_try_again_button_content_padding_vertical)


    val isLottiePlaying = remember {
        mutableStateOf(true)
    }
    val animationSpeed by remember {
        mutableStateOf(1f)
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.network_error_anim)
    )
    val lottieAnimation by animateLottieCompositionAsState(
        // pass the composition created above
        composition,
        // Iterates Forever
        iterations = LottieConstants.IterateForever,
        // Lottie and pause/play
        isPlaying = isLottiePlaying.value,
        // Increasing the speed of change Lottie
        speed = animationSpeed,
        restartOnPlay = false

    )
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (icon, title, description, button) = createRefs()

        LottieAnimation(
            composition,
            lottieAnimation,
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(anchor = parent.top, margin = errIconMarginTop)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    bottom.linkTo(anchor = title.top)
                }
                .height(height = errIconHeight)
        )

        Text(
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(icon.bottom, titleMarginTop)
            },
            textAlign = TextAlign.Center,
            text = networkErrorTitle,
            color = InvoicesAppTheme.colors.body2PrimaryText,
            style = InvoicesAppTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier.constrainAs(description) {
                start.linkTo(anchor = parent.start, margin = descriptionMarginHor)
                end.linkTo(anchor = parent.end, margin = descriptionMarginHor)
                top.linkTo(anchor = title.bottom, margin = descriptionMarginTop)
            },
            textAlign = TextAlign.Center,
            text = networkErrorDescription,
            color = InvoicesAppTheme.colors.errorColor,
            style = InvoicesAppTheme.typography.labelMedium,
        )
        Button(
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .wrapContentHeight(align = Alignment.CenterVertically)
                .constrainAs(button) {
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    bottom.linkTo(anchor = parent.bottom, margin = tryAgainButtonMarginBottom)
                }
                .background(InvoicesAppTheme.colors.primaryBackground),
            contentPadding = PaddingValues(
                horizontal = tryAgainButtonContentPaddingHor,
                vertical = tryAgainButtonContentPaddingVertical
            ),
            onClick = {
                isLottiePlaying.value = false
                tryAgainButtonClicked.invoke()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = InvoicesAppTheme.colors.buttonPrimary
            ),
            shape = InvoicesAppTheme.roundedCornerShape.shapeSmall,
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = tryAgainButtonText.uppercase(),
                style = InvoicesAppTheme.typography.labelMedium,
                color = InvoicesAppTheme.colors.errorColor,
                maxLines = 1,
            )
        }
    }
}


@Preview(
    name = "NetworkErrorScreenLightPreview",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun NetworkErrorScreenLightPreview() {

    InvoicesAppExerciseTheme(darkTheme = false) {
        NetworkErrorScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(InvoicesAppTheme.colors.primaryBackground)
        ) {
            // tryAgainButtonClicked
        }
    }
}

@Preview(
    name = "NetworkErrorScreenDarkPreview",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun NetworkErrorScreenDarkPreview() {
    InvoicesAppExerciseTheme(darkTheme = true) {
        NetworkErrorScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(InvoicesAppTheme.colors.primaryBackground)
        ) {
            // tryAgainButtonClicked
        }
    }
}