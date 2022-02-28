package com.iamkurtgoz.application.base

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iamkurtgoz.domain.data.remote.network.exception.NoConnectivityException
import com.iamkurtgoz.domain.data.remote.network.exception.NullableException
import com.iamkurtgoz.domain.remote.resource.FlowResource
import com.iamkurtgoz.application.remote.extension.getErrorMessage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel(
    @ApplicationContext private var _application: Application
) : AndroidViewModel(_application) {


    private val _baseState: MutableState<ViewModelState> = mutableStateOf(ViewModelState())
    val baseState: State<ViewModelState> = _baseState

    //region Paging Request
    inline fun <T : Any> Flow<PagingData<T>>.sendRequestPaging(crossinline resultHandler: suspend (PagingData<T>) -> Unit) {
        cachedIn(viewModelScope).onEach { resultHandler.invoke(it) }.launchIn(viewModelScope)
    }
    //endregion

    //region One Shot
    fun <T> Flow<FlowResource<T>>.justDoIt() = launchIn(viewModelScope)

    fun <T> Flow<FlowResource<T>>.justDoIt(requestType: RequestType) {
        onEach { it.sendAllStates(requestType) }.launchIn(viewModelScope)
    }

    inline fun <T> Flow<FlowResource<T>>.sendRequest(crossinline resultHandler: suspend (FlowResource<T>) -> Unit) {
        onEach { resultHandler.invoke(it) }.launchIn(viewModelScope)
    }

    inline fun <T> Flow<FlowResource<T>>.sendRequestOnlySuccess(
        requestType: RequestType = RequestType.FOR_PROGRESS_MANAGER,
        crossinline resultHandler: suspend (T) -> Unit
    ) {
        onEach {
            when (it) {
                is FlowResource.Success -> resultHandler.invoke(it.data)
                else -> it.sendAllStates(requestType)
            }
        }.launchIn(viewModelScope)
    }

    inline fun <T> Flow<BaseViewState>.sendRequestCombine(crossinline resultHandler: suspend (T) -> Unit) {
        onEach { resultHandler.invoke(it as T) }.launchIn(viewModelScope)
    }

    inline fun <T> Flow<BaseViewState>.sendRequestCombineOnlySuccess(
        requestType: RequestType = RequestType.FOR_PROGRESS_MANAGER,
        crossinline resultHandler: suspend (T) -> Unit
    ) {
        onEach {
            if (it.getLoadingStatus()) {
                showLoading(requestType)
            } else if (it.isErrorStatus()) {
                showError(
                    title = null,
                    message = it.getError()?.message ?: "",
                    requestType = requestType
                )
            } else if (it.getSuccessStatus()) {
                resultHandler.invoke(it as T)
            }
        }.launchIn(viewModelScope)
    }

    fun <T> FlowResource<T>.sendAllStates(requestType: RequestType = RequestType.FOR_PROGRESS_MANAGER) {
        when (this) {
            is FlowResource.Loading -> showLoading(requestType)
            is FlowResource.Error -> showError(exception = exception, requestType = requestType)
        }
    }
    //endregion

    //region states
    fun showLoading(requestType: RequestType = RequestType.FOR_PROGRESS_MANAGER) {
        _baseState.value = ViewModelState(showDialog = ShowLoadingStateModel(requestType))
    }

    fun showAlert(
        title: String? = null,
        message: String,
        cancelable: Boolean = true,
        requestType: RequestType? = null
    ) {
        _baseState.value = ViewModelState(
            alert = ShowAlertStateModel(title, message, cancelable, requestType)
        )
    }

    fun showError(
        title: String? = null,
        exception: Throwable,
        type: String? = null,
        cancelable: Boolean = true,
        requestType: RequestType? = null
    ) {
        exception.printStackTrace()
        when (exception) {
            is NoConnectivityException -> showNoConnection(
                title,
                exception.getErrorMessage(getApplication())
            )
            is NullableException -> showNullable(title, exception)
            else -> _baseState.value = ViewModelState(
                error = ShowErrorStateModel(
                    title,
                    exception.getErrorMessage(getApplication()),
                    type,
                    cancelable,
                    requestType
                )
            )
        }
    }

    fun showError(
        title: String?,
        message: String,
        type: String? = null,
        cancelable: Boolean = true,
        requestType: RequestType? = null
    ) {
        _baseState.value = ViewModelState(
            error = ShowErrorStateModel(
                title,
                message,
                type,
                cancelable,
                requestType
            )
        )
    }

    fun showEmpty(
        title: String? = null,
        message: String,
        cancelable: Boolean = true,
        requestType: RequestType? = null
    ) {
        _baseState.value = ViewModelState(empty = ShowEmptyStateModel(title, message, cancelable, requestType))
    }

    fun showNullable(
        title: String? = null,
        exception: Throwable,
        cancelable: Boolean = true,
        requestType: RequestType? = null
    ) {
        showNullable(title, exception.getErrorMessage(getApplication()), cancelable, requestType)
    }

    fun showNullable(
        title: String? = null,
        message: String,
        cancelable: Boolean = true,
        requestType: RequestType? = null
    ) {
        _baseState.value = ViewModelState(
            empty = ShowEmptyStateModel(
                title,
                message,
                cancelable,
                requestType
            )
        )
    }

    fun showNoConnection(
        title: String? = null,
        exception: Throwable,
        cancelable: Boolean = true,
        requestType: RequestType? = null
    ) {
        showNoConnection(title, exception.getErrorMessage(getApplication()), cancelable, requestType)
    }

    fun showNoConnection(
        title: String? = null,
        message: String,
        cancelable: Boolean = true,
        requestType: RequestType? = null
    ) {
        _baseState.value = ViewModelState(noConnection = ShowNoConnectionStateModel(title, message, cancelable, requestType))
    }

    fun showContent() {
        _baseState.value = ViewModelState()
    }
    //endregion
}