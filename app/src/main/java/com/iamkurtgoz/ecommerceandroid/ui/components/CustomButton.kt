package com.iamkurtgoz.ecommerceandroid.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamkurtgoz.ecommerceandroid.ui.theme.red500
import com.iamkurtgoz.ecommerceandroid.ui.theme.red500Light
import com.iamkurtgoz.ecommerceandroid.ui.theme.shapes
import com.iamkurtgoz.ecommerceandroid.ui.theme.typography

@Composable
fun CustomButton(
    title: String,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = red500,
        contentColor = Color.White,
    ),
    onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
            .height(45.dp),
        shape = shapes.medium,
        colors = colors
    ) {
        Text(
            text = title,
            style = typography.button
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StockButtonPreview() {
    CustomButton(title = "Ekle") {}
}