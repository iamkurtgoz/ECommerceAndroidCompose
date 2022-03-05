package com.iamkurtgoz.ecommerceandroid.ui.home.flow

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.iamkurtgoz.ecommerceandroid.R
import com.iamkurtgoz.ecommerceandroid.ui.components.CustomBoldTitleText
import com.iamkurtgoz.ecommerceandroid.ui.components.CustomProductCard
import com.iamkurtgoz.ecommerceandroid.ui.components.ProgressManager
import com.iamkurtgoz.ecommerceandroid.ui.components.SearchView
import com.iamkurtgoz.ecommerceandroid.ui.height
import com.iamkurtgoz.ecommerceandroid.ui.size
import com.iamkurtgoz.ecommerceandroid.ui.theme.screenWidth
import com.iamkurtgoz.ecommerceandroid.ui.theme.spacing
import com.iamkurtgoz.ecommerceandroid.ui.width

@Composable
fun FlowScreenView(
    viewModel: FlowViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val categoryList = viewModel.categoryListState.value
    val electronicsList = viewModel.electronicsListState.value
    val jeweleryList = viewModel.jeweleryListState.value
    val menClothingList = viewModel.menClothingListState.value
    val womenClothingList = viewModel.womenClothingListState.value

    ProgressManager(baseViewModel = viewModel) {

        LazyColumn {

            item {
                SearchView(
                    text = viewModel.searchState,
                    placeholder = context.getString(R.string.search_placeholder),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.medium)
                )
            }

            item {
                CustomBoldTitleText(
                    title = viewModel.getElectronicsTitle().uppercase(),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.extraSmall)
                )
            }

            item {
                LazyRow {
                    with(electronicsList) {
                        items(size) {
                            val productModel = get(it)
                            val isLast = it == size.minus(1)
                            println(productModel.isFavorite)
                            CustomProductCard(
                                productModel = productModel,
                                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
                                    .padding(end = if (isLast) MaterialTheme.spacing.medium else MaterialTheme.spacing.default),
                                onFavoriteClicked = { model -> viewModel.setFavorite(model) }
                            )
                        }
                    }
                }
            }

            item {
                CustomBoldTitleText(
                    title = viewModel.getJeweleryTitle().uppercase(),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.small)
                )
            }

            item {
                LazyRow {
                    with(jeweleryList) {
                        items(size) {
                            val productModel = get(it)
                            val isLast = it == size.minus(1)
                            CustomProductCard(
                                productModel = productModel,
                                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
                                    .padding(end = if (isLast) MaterialTheme.spacing.medium else MaterialTheme.spacing.default),
                                onFavoriteClicked = { model -> viewModel.setFavorite(model) }
                            )
                        }
                    }
                }
            }

            item {
                CustomBoldTitleText(
                    title = viewModel.getMenClothingTitle().uppercase(),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.small)
                )
            }

            item {
                LazyRow {
                    with(menClothingList) {
                        items(size) {
                            val productModel = get(it)
                            val isLast = it == size.minus(1)
                            CustomProductCard(
                                productModel = productModel,
                                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
                                    .padding(end = if (isLast) MaterialTheme.spacing.medium else MaterialTheme.spacing.default),
                                onFavoriteClicked = { model -> viewModel.setFavorite(model) }
                            )
                        }
                    }
                }
            }

            item {
                CustomBoldTitleText(
                    title = viewModel.getWomenClothingTitle().uppercase(),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.small)
                )
            }

            item {
                LazyRow {
                    with(womenClothingList) {
                        items(size) {
                            val productModel = get(it)
                            val isLast = it == size.minus(1)
                            CustomProductCard(
                                productModel = productModel,
                                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
                                    .padding(end = if (isLast) MaterialTheme.spacing.medium else MaterialTheme.spacing.default)
                                    .padding(bottom = MaterialTheme.spacing.small),
                                onFavoriteClicked = { model -> viewModel.setFavorite(model) }
                            )
                        }
                    }
                }
            }

        }

    }
}