package com.iamkurtgoz.ecommerceandroid.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.iamkurtgoz.ecommerceandroid.R
import com.iamkurtgoz.ecommerceandroid.ui.theme.ECommerceAndroidTheme
import com.iamkurtgoz.ecommerceandroid.ui.theme.gray200
import com.iamkurtgoz.ecommerceandroid.ui.theme.gray700
import com.iamkurtgoz.ecommerceandroid.ui.theme.red500

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    size: Dp = 12.dp
) {
    var ratingState by remember {
        mutableStateOf(rating)
    }

    var selected by remember {
        mutableStateOf(false)
    }

    Row {
        for (i in 1..5) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_black),
                contentDescription = "star",
                modifier = modifier
                    .width(size)
                    .height(size),
                tint = if (i <= ratingState) red500 else gray200
            )
        }
    }
}

@Preview
@Composable
fun RatingBarPreview() {
    ECommerceAndroidTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            RatingBar(rating = 3)
        }
    }
}