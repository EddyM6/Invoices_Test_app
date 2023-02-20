package com.xmm_homework.core.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.xmm_homework.core.ui.theme.InvoicesAppExerciseTheme
import com.xmm_homework.core.ui.theme.InvoicesAppTheme


@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {

        CircularProgressIndicator(
            modifier = Modifier
                .align(
                    Alignment.Center
                ),
            color = InvoicesAppTheme.colors.buttonPrimary
        )
    }
}

@Preview(
    name = "Loading Screen Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun LoadingScreenLightPreview() {
    InvoicesAppExerciseTheme(darkTheme = false) {
        Box(modifier = Modifier.fillMaxSize()) {

            CircularProgressIndicator(
                modifier = Modifier
                    .align(
                        Alignment.Center
                    ),
                color = InvoicesAppTheme.colors.buttonPrimary,
                progress = 0.75f
            )
        }
    }
}