package com.iamkurtgoz.application.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productEntity")
data class ProductEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "category") val category: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "image") val image: String = "",
    @ColumnInfo(name = "price") val price: Double = 0.0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "rate") val rate: Double = 0.0
)