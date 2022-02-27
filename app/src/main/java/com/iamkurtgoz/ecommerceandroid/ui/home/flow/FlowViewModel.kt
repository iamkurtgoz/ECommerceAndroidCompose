package com.iamkurtgoz.ecommerceandroid.ui.home.flow

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.iamkurtgoz.application.base.BaseViewModel
import com.iamkurtgoz.application.base.RequestType
import com.iamkurtgoz.application.useCase.ProductCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FlowViewModel @Inject constructor(
    application: Application,
    private val productCategoriesUseCase: ProductCategoriesUseCase
) : BaseViewModel(application) {

    private val _state: MutableState<FlowState> = mutableStateOf(FlowState.None)
    val state: State<FlowState> = _state

    private val _categoryListState: MutableState<List<String>> = mutableStateOf(emptyList())
    val categoryListState: State<List<String>> = _categoryListState

    val searchState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())

    init {
        getCategories()
    }

    private fun getCategories(){
        productCategoriesUseCase().sendRequestOnlySuccess(requestType = RequestType.FOR_PROGRESS_MANAGER) {
            showContent()
            _categoryListState.value = it
        }
    }

    fun clearState(){
        _state.value = FlowState.None
    }
}
