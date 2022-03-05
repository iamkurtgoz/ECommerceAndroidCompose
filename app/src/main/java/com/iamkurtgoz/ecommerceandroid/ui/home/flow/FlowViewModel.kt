package com.iamkurtgoz.ecommerceandroid.ui.home.flow

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.application.base.BaseViewModel
import com.iamkurtgoz.application.base.RequestType
import com.iamkurtgoz.application.extensions.getError
import com.iamkurtgoz.application.extensions.getLoading
import com.iamkurtgoz.application.extensions.getSuccess
import com.iamkurtgoz.application.local.ECommerceLocalDataSource
import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.application.useCase.ProductCategoriesUseCase
import com.iamkurtgoz.application.useCase.ProductFavoriteUseCase
import com.iamkurtgoz.application.useCase.ProductUseCase
import com.iamkurtgoz.ecommerceandroid.ui.home.flow.state.FlowState
import com.iamkurtgoz.ecommerceandroid.ui.home.flow.state.FlowViewState
import com.iamkurtgoz.ecommerceandroid.ui.home.flow.state.flowPageCombiner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowViewModel @Inject constructor(
    application: Application,
    private val productCategoriesUseCase: ProductCategoriesUseCase,
    private val productUseCase: ProductUseCase,
    private val productFavoriteUseCase: ProductFavoriteUseCase
) : BaseViewModel(application) {

    private val _state: MutableState<FlowState> = mutableStateOf(FlowState.None)
    val state: State<FlowState> = _state

    val searchState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())

    private val _categoryListState: MutableState<List<String>> = mutableStateOf(emptyList())
    val categoryListState: State<List<String>> = _categoryListState

    private val _electronicsListState: MutableState<List<ProductModel>> = mutableStateOf(emptyList())
    val electronicsListState: State<List<ProductModel>> = _electronicsListState

    private val _jeweleryListState: MutableState<List<ProductModel>> = mutableStateOf(emptyList())
    val jeweleryListState: State<List<ProductModel>> = _jeweleryListState

    private val _menClothingListState: MutableState<List<ProductModel>> = mutableStateOf(emptyList())
    val menClothingListState: State<List<ProductModel>> = _menClothingListState

    private val _womenClothingListState: MutableState<List<ProductModel>> = mutableStateOf(emptyList())
    val womenClothingListState: State<List<ProductModel>> = _womenClothingListState

    init {
        getCategories()
    }

    private fun getCategories(){
        productCategoriesUseCase().sendRequestOnlySuccess(requestType = RequestType.FOR_PROGRESS_MANAGER) {
            _categoryListState.value = it
            getProducts(it)
        }
    }

    fun getElectronicsTitle() = _categoryListState.value.find { it.contains("electronics") } ?: "electronics"
    fun getJeweleryTitle() = _categoryListState.value.find { it.contains("jewelery") } ?: "jewelery"
    fun getMenClothingTitle() = _categoryListState.value.find { it.contains("men's clothing") } ?: "men's clothing"
    fun getWomenClothingTitle() = _categoryListState.value.find { it.contains("women's clothing") } ?: "women's clothing"

    private fun getProducts(list: List<String>){
        combine(
            productUseCase(getElectronicsTitle()),
            productUseCase(getJeweleryTitle()),
            productUseCase(getMenClothingTitle()),
            productUseCase(getWomenClothingTitle()),
            ::flowPageCombiner
        ).sendRequestCombine<FlowViewState> {
            if (it.getLoadingStatus()) {
                showLoading(RequestType.FOR_PROGRESS_MANAGER)
            } else if (it.isErrorStatus()) {
                showError(
                    title = null,
                    message = it.getError()?.message ?: "",
                    requestType = RequestType.FOR_DIALOG
                )
            } else if (it.getSuccessStatus()) {
                _electronicsListState.value = it.getDataElectronics() ?: emptyList()
                _jeweleryListState.value = it.getDataJewelry() ?: emptyList()
                _menClothingListState.value = it.getDataMenClothing() ?: emptyList()
                _womenClothingListState.value = it.getDataWomenClothing() ?: emptyList()
                showContent()
            }
        }
    }

    fun setFavorite(productModel: ProductModel) = viewModelScope.launch {
        productModel.id?.let { id ->
            productFavoriteUseCase(id).sendRequest { it ->
                it.getLoading { showLoading(RequestType.FOR_DIALOG) }
                it.getSuccess {
                    updateModel(it)
                    showContent()
                }
                it.sendAllStates(RequestType.FOR_DIALOG)
            }
        }
    }

    private fun updateModel(productModel: ProductModel) {
        if (_electronicsListState.value.any { it.id == productModel.id }){
            _electronicsListState.value = _electronicsListState.value.map { if (it.id == productModel.id) productModel else it }
        }
        if (_jeweleryListState.value.any { it.id == productModel.id }){
            _jeweleryListState.value = _jeweleryListState.value.map { if (it.id == productModel.id) productModel else it }
        }
        if (_menClothingListState.value.any { it.id == productModel.id }){
            _menClothingListState.value = _menClothingListState.value.map { if (it.id == productModel.id) productModel else it }
        }
        if (_womenClothingListState.value.any { it.id == productModel.id }){
            _womenClothingListState.value = _womenClothingListState.value.map { if (it.id == productModel.id) productModel else it }
        }
    }

    fun clearState(){
        _state.value = FlowState.None
    }
}
