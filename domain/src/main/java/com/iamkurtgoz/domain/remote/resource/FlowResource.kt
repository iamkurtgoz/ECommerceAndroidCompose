package com.iamkurtgoz.domain.remote.resource

sealed class FlowResource<out D> {
    data class Success<D>(val data: D) : FlowResource<D>()
    data class Error(val exception: Throwable) : FlowResource<Nothing>()
    object NullObject : FlowResource<Nothing>()
    object Loading : FlowResource<Nothing>()
}