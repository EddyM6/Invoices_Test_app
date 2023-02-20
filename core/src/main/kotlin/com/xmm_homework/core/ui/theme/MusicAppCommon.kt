package com.xmm_homework.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class InvoicesAppColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val primaryTextToolBar: Color,
    val secondaryTextToolBar: Color,
    val buttonPrimary: Color,
    val body2PrimaryText: Color,
    val body2SecondaryText: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color
)

data class InvoicesAppTypography(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
)

data class InvoicesAppRoundedCornerShape(
    val shapeLarge: RoundedCornerShape,
    val shapeMedium: RoundedCornerShape,
    val shapeSmall: RoundedCornerShape,
)

object InvoicesAppTheme {
    val colors: InvoicesAppColors
        @Composable
        get() = LocalInvoicesAppColors.current

    val typography: InvoicesAppTypography
        @Composable
        get() = LocalInvoicesAppTypography.current

    val roundedCornerShape: InvoicesAppRoundedCornerShape
        @Composable
        get() = LocalInvoicesAppRoundedCornerShape.current
}

enum class InvoicesAppStyle {
    Main
}


val LocalInvoicesAppColors = staticCompositionLocalOf<InvoicesAppColors> {
    error("No colors provided")
}
val LocalInvoicesAppTypography = staticCompositionLocalOf<InvoicesAppTypography> {
    error("No font provided")
}

val LocalInvoicesAppRoundedCornerShape = staticCompositionLocalOf<InvoicesAppRoundedCornerShape> {
    error("No shape provided")
}
