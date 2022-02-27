package com.iamkurtgoz.ecommerceandroid.ui.theme

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val MaterialTheme.configuration: Configuration
    @Composable
    @ReadOnlyComposable
    get() = LocalConfiguration.current

val MaterialTheme.screenWidth: Int
    @Composable
    @ReadOnlyComposable
    get() = configuration.screenWidthDp

val MaterialTheme.screenHeight: Int
    @Composable
    @ReadOnlyComposable
    get() = configuration.screenHeightDp