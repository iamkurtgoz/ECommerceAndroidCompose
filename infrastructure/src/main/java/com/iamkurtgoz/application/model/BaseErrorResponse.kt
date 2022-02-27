package com.iamkurtgoz.application.model

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse (
    val statusCode: Int = 200,
    @SerializedName("message")
    private val _message: String = "",
    val errors: ArrayList<String> = arrayListOf(),
    val type: String?
): Throwable(_message) {

    override val message: String
        get() = _message
}