package com.iamkurtgoz.ecommerceandroid.ui.home.basket

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.iamkurtgoz.application.base.BaseViewModel
import com.iamkurtgoz.application.base.RequestType
import com.iamkurtgoz.application.extensions.getLoading
import com.iamkurtgoz.application.extensions.getSuccess
import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.application.useCase.ProductFavoritesListUseCase
import com.iamkurtgoz.ecommerceandroid.ui.home.flow.state.FlowState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    application: Application,
    private val productFavoritesListUseCase: ProductFavoritesListUseCase
) : BaseViewModel(application) {

    private val _state: MutableState<BasketState> = mutableStateOf(BasketState.None)
    val state: State<BasketState> = _state

    private val _favoritesListState: MutableState<List<ProductModel>> = mutableStateOf(emptyList())
    val favoritesListState: State<List<ProductModel>> = _favoritesListState

    init {
        getFavorites()
    }

    private fun getFavorites(){
        productFavoritesListUseCase().sendRequestOnlySuccess {
            _favoritesListState.value = it
            showContent()
        }
    }
}
