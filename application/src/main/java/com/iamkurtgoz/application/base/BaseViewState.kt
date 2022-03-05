package com.iamkurtgoz.application.base

import com.iamkurtgoz.domain.remote.resource.FlowResource

open class BaseViewState(private vararg var flows: FlowResource<*>) {

    fun getLoadingStatus(): Boolean {
        flows.forEach {
            if (it is FlowResource.Loading) {
                return true
            }
        }
        return false
    }

    fun isErrorStatus(): Boolean {
        flows.forEach {
            if (it is FlowResource.Error) {
                return true
            }
        }
        return false
    }

    fun getSuccessStatus(): Boolean {
        flows.forEach {
            if (it is FlowResource.Success) {
                return true
            }
        }
        return false
    }

    fun getError(): Throwable? {
        flows.forEach {
            if (it is FlowResource.Error) {
                return (it as? FlowResource.Error?)?.exception
            }
        }
        return null
    }
}
