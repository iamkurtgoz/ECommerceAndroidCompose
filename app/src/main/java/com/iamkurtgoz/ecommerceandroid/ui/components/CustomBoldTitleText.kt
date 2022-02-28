package com.iamkurtgoz.ecommerceandroid.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iamkurtgoz.ecommerceandroid.ui.theme.boldTitleFont
import com.iamkurtgoz.ecommerceandroid.ui.theme.spacing

@Composable
fun CustomBoldTitleText(
    title: String
) {
    Text(
        text = title,
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .padding(top = MaterialTheme.spacing.extraSmall),
        style = boldTitleFont
    )
}