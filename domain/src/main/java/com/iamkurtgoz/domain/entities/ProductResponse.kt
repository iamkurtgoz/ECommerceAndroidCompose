package com.iamkurtgoz.domain.entities

data class ProductResponse(
    val category: String?,
    val description: String?,
    val id: Int?,
    val image: String?,
    val price: Double?,
    val rating: RatingResponse?,
    val title: String?
)