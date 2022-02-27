package com.iamkurtgoz.ecommerceandroid.ui.home.flow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iamkurtgoz.ecommerceandroid.R
import com.iamkurtgoz.ecommerceandroid.ui.components.ProgressManager
import com.iamkurtgoz.ecommerceandroid.ui.components.SearchView
import com.iamkurtgoz.ecommerceandroid.ui.theme.boldTitleFont
import com.iamkurtgoz.ecommerceandroid.ui.theme.spacing

@Composable
fun FlowScreenView(
    viewModel: FlowViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val categoryList = viewModel.categoryListState.value

    ProgressManager(baseViewModel = viewModel) {

        Column {

            SearchView(
                text = viewModel.searchState,
                placeholder = context.getString(R.string.search_placeholder),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                    .padding(top = MaterialTheme.spacing.medium)
            )


            LazyColumn {
                items(categoryList.size) {
                    Text(
                        text = categoryList[it].uppercase(),
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.medium)
                            .padding(top = MaterialTheme.spacing.extraSmall),
                        style = boldTitleFont
                    )
                }
            }

        }
    }
}