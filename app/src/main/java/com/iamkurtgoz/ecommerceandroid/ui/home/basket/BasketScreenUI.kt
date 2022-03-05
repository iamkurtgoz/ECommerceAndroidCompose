package com.iamkurtgoz.ecommerceandroid.ui.home.basket

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iamkurtgoz.ecommerceandroid.R
import com.iamkurtgoz.ecommerceandroid.ui.components.CustomBoldTitleText
import com.iamkurtgoz.ecommerceandroid.ui.components.CustomProductCardDetail
import com.iamkurtgoz.ecommerceandroid.ui.components.ProgressManager
import com.iamkurtgoz.ecommerceandroid.ui.theme.spacing

@Composable
fun BasketScreenView(
    viewModel: BasketViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    val state = viewModel.state.value
    val favoritesList = viewModel.favoritesListState.value

    ProgressManager(baseViewModel = viewModel) {

        LazyColumn {

            item {
                CustomBoldTitleText(
                    title = context.getString(R.string.favorites).uppercase(),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.medium)
                )
            }

            with(favoritesList) {
                items(size) {
                    val productModel = get(it)
                    println(productModel.isFavorite)
                    CustomProductCardDetail(
                        productModel = productModel,
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.medium)
                    )
                }
            }
            
            item {
                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium))
            }
        }
    }
}