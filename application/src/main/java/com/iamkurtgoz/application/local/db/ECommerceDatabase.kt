package com.iamkurtgoz.application.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamkurtgoz.application.local.dao.ProductCategoryDAO
import com.iamkurtgoz.application.local.dao.ProductDAO
import com.iamkurtgoz.application.local.entity.ProductCategoryEntity
import com.iamkurtgoz.application.local.entity.ProductEntity

@Database(
    entities = [ProductCategoryEntity::class, ProductEntity::class],
    version = ECommerceDatabase.VERSION,
    exportSchema = false
)
abstract class ECommerceDatabase : RoomDatabase() {

    abstract fun getProductCategoryDAO(): ProductCategoryDAO
    abstract fun getProductDAO(): ProductDAO

    companion object {
        const val VERSION = 1
        const val DB_NAME = "ECommerceDatabase.db"
    }
}