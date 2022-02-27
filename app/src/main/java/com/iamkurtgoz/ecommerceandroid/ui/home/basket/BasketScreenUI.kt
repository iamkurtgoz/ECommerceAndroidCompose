package com.iamkurtgoz.ecommerceandroid.ui.home.basket

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun BasketScreenView(
    viewModel: BasketViewModel = hiltViewModel(),
    navController: NavController
) {
    Text(text = "Basket", modifier = Modifier.fillMaxSize())
}