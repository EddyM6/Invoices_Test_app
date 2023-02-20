package com.xmm_homework.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun InvoicesAppExerciseTheme(
    style: InvoicesAppStyle = InvoicesAppStyle.Main,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                InvoicesAppStyle.Main -> baseDarkPalette
            }
        }
        false -> {
            when (style) {
                InvoicesAppStyle.Main -> baseLightPalette
            }
        }
    }

    CompositionLocalProvider(
        LocalInvoicesAppColors provides colors,
        LocalInvoicesAppTypography provides typography,
        LocalInvoicesAppRoundedCornerShape provides shapes,
        content = content
    )
}