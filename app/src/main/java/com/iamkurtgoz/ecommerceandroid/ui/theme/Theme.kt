package com.iamkurtgoz.ecommerceandroid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = black,
    primaryVariant = black,
    onPrimary = white,
    secondary = red500,
    secondaryVariant = green500,
    onSecondary = black,
    surface = white,
    onSurface = black,
    background = white,
    onBackground = black
)

private val DarkColorPalette = darkColors(
    primary = black,
    primaryVariant = black,
    onPrimary = white,
    secondary = red500,
    secondaryVariant = green500,
    onSecondary = black,
    surface = black,
    onSurface = white,
    background = blackLight,
    onBackground = white,
)

@Composable
fun textColor() = if (MaterialTheme.colors.isLight) Color.Black else Color.White
@Composable
fun textColorSecondary() = if (MaterialTheme.colors.isLight) gray700 else gray200

@Composable
fun ECommerceAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}