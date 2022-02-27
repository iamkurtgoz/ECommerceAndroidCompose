package com.iamkurtgoz.application.base

data class ViewModelState(
    val showDialog: ShowLoadingStateModel? = null,
    val alert: ShowAlertStateModel? = null,
    val warning: ShowWarningStateModel? = null,
    val error: ShowErrorStateModel? = null,
    val empty: ShowEmptyStateModel? = null,
    val noConnection: ShowNoConnectionStateModel? = null
)

data class ShowLoadingStateModel(
    val requestType: RequestType? = null
)

data class ShowAlertStateModel(
    val title: String? = null,
    val message: String,
    val cancelable: Boolean = true,
    val requestType: RequestType? = null
)

data class ShowWarningStateModel(
    val title: String? = null,
    val message: String,
    val cancelable: Boolean = true,
    val requestType: RequestType? = null
)

data class ShowErrorStateModel(
    val title: String? = null,
    val message: String,
    val type: String? = null,
    val cancelable: Boolean = true,
    val requestType: RequestType? = null
)

data class ShowEmptyStateModel(
    val title: String? = null,
    val message: String,
    val cancelable: Boolean = true,
    val requestType: RequestType? = null
)

data class ShowNoConnectionStateModel(
    val title: String? = null,
    val message: String,
    val cancelable: Boolean = true,
    val requestType: RequestType? = null
)