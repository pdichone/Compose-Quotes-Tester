package com.bawp.quotes_test.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = black,
    onPrimary = white,
    primaryVariant = paleBlack

                                         )

private val LightColorPalette = lightColors(
    primary = white,
    onPrimary = black,
    primaryVariant = paleWhite
                                           )

@Composable
fun QuotesTestTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}