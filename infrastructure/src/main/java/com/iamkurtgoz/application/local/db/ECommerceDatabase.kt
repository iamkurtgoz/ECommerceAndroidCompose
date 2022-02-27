package com.iamkurtgoz.application.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamkurtgoz.application.local.ProductCategoryDAO
import com.iamkurtgoz.application.local.model.ProductCategoryEntity

@Database(
    entities = [ProductCategoryEntity::class],
    version = ECommerceDatabase.VERSION,
    exportSchema = false
)
abstract class ECommerceDatabase : RoomDatabase() {

    abstract fun getProductCategoryDAO(): ProductCategoryDAO

    companion object {
        const val VERSION = 1
        const val DB_NAME = "ECommerceDatabase.db"
    }
}