package com.iamkurtgoz.application.extensions

import androidx.paging.PagingData
import androidx.paging.map
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import com.iamkurtgoz.domain.extension.isNull
import com.iamkurtgoz.domain.remote.resource.FlowResource
import com.iamkurtgoz.application.model.BaseErrorResponse
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import java.lang.Exception

fun <T> prepare(gson: Gson, dispatcher: CoroutineDispatcher = Dispatchers.Main, block: suspend () -> Response<T>): Flow<FlowResource<T>> = flow {
    val response = block.invoke()
    if (response.isSuccessful && response.body() != null) {
        emit(FlowResource.Success(response.body()!!))
    } else {
        val error: BaseErrorResponse? = gson.fromJson(response.errorBody()?.string(), BaseErrorResponse::class.java)
        emit(FlowResource.Error(error!!))
    }
}.onStart { emit(FlowResource.Loading) }
    .catch { throwable ->
        val error = throwable.localizedMessage?.let { errorMessage ->
            BaseErrorResponse(400, errorMessage, arrayListOf(errorMessage), "")
        } ?: kotlin.run {
            BaseErrorResponse(400, "Error unknown!", arrayListOf("Error unknown!"), "")
        }
        emit(FlowResource.Error(error))
    }.flowOn(dispatcher)

fun <T> prepare(block: suspend () -> T): Flow<FlowResource<T>> = flow {
    val response = block.invoke()
    if (response.isNull()){
        emit(FlowResource.NullObject)
    } else {
        try {
            emit(FlowResource.Success(response))
        } catch (e: Exception){
            emit(FlowResource.Error(e))
        }
    }
}.onStart { emit(FlowResource.Loading) }
    .catch { throwable ->
        val error = throwable.localizedMessage?.let { errorMessage ->
            BaseErrorResponse(400, errorMessage, arrayListOf(errorMessage), "")
        } ?: kotlin.run {
            BaseErrorResponse(400, "Error unknown!", arrayListOf("Error unknown!"), "")
        }
        emit(FlowResource.Error(error))
    }.flowOn(Dispatchers.IO)

inline fun <T, R> Flow<FlowResource<T>>.mapForFlow(crossinline transform: suspend (T) -> R): Flow<FlowResource<R>> {
    val flow = this
    return flow.map { flowMap ->
        return@map when (flowMap) {
            is FlowResource.Success -> FlowResource.Success(transform(flowMap.data))
            is FlowResource.Error -> FlowResource.Error(flowMap.exception)
            is FlowResource.Loading -> FlowResource.Loading
            is FlowResource.NullObject -> FlowResource.NullObject
        }
    }
}

fun <T : Any, R : Any> Flow<PagingData<T>>.mapForFlowPaging(transform: (T) -> R): Flow<PagingData<R>> {
    val flow = this
    return flow.map {
        it.map { transform(it) }
    }
}