package com.iamkurtgoz.ecommerceandroid.ui.home.flow

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.iamkurtgoz.ecommerceandroid.ui.components.ProgressManager
import com.iamkurtgoz.ecommerceandroid.ui.components.SearchView
import com.iamkurtgoz.ecommerceandroid.ui.height
import com.iamkurtgoz.ecommerceandroid.ui.size
import com.iamkurtgoz.ecommerceandroid.ui.theme.screenWidth
import com.iamkurtgoz.ecommerceandroid.ui.theme.spacing

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
                CustomBoldTitleText(title = viewModel.getElectronicsTitle().uppercase())
            }

            item {
                LazyRow {
                    items(electronicsList.size) {
                        Card(
                            elevation = 4.dp,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                                .size(MaterialTheme.screenWidth * 0.5)
                        ) {
                            Image(painter = rememberImagePainter(electronicsList[it].image), contentDescription = null)
                        }
                    }
                }
            }

            item {
                CustomBoldTitleText(title = viewModel.getJeweleryTitle().uppercase())
            }

            item {
                LazyRow {
                    items(jeweleryList.size) {
                        Card(
                            elevation = 4.dp,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                                .size(MaterialTheme.screenWidth * 0.5)
                        ) {
                            Image(painter = rememberImagePainter(jeweleryList[it].image), contentDescription = null)
                        }
                    }
                }
            }

            item {
                CustomBoldTitleText(title = viewModel.getMenClothingTitle().uppercase())
            }

            item {
                LazyRow {
                    items(menClothingList.size) {
                        Card(
                            elevation = 4.dp,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                                .size(MaterialTheme.screenWidth * 0.5)
                        ) {
                            Image(painter = rememberImagePainter(menClothingList[it].image), contentDescription = null)
                        }
                    }
                }
            }

            item {
                CustomBoldTitleText(title = viewModel.getWomenClothingTitle().uppercase())
            }

            item {
                LazyRow {
                    items(womenClothingList.size) {
                        Card(
                            elevation = 4.dp,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                                .size(MaterialTheme.screenWidth * 0.5)
                        ) {
                            Image(painter = rememberImagePainter(womenClothingList[it].image), contentDescription = null)
                        }
                    }
                }
            }

        }

    }
}