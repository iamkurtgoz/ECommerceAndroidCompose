package com.iamkurtgoz.application.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productCategoryEntity")
data class ProductCategoryEntity(
    @PrimaryKey val uid: String = "",
    @ColumnInfo(name = "title") val title: String
)
