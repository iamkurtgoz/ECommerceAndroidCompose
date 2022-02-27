package com.iamkurtgoz.application.extensions

import com.iamkurtgoz.domain.remote.resource.FlowResource

suspend inline fun <T> FlowResource<T>.getLoading(crossinline block: suspend () -> Unit) {
    if (this is FlowResource.Loading) {
        block.invoke()
    }
}

suspend inline fun <T> FlowResource<T>.getSuccess(crossinline block: suspend (T) -> Unit) {
    if (this is FlowResource.Success) {
        block.invoke(this.data)
    }
}

suspend inline fun <T> FlowResource<T>.getError(crossinline block: suspend (exception: Throwable) -> Unit) {
    if (this is FlowResource.Error) {
        block.invoke(this.exception)
    }
}

suspend inline fun <T> FlowResource<T>.getNullObject(crossinline block: suspend () -> Unit) {
    if (this is FlowResource.NullObject) {
        block.invoke()
    }
}