package com.iamkurtgoz.application.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamkurtgoz.application.local.entity.ProductCategoryEntity
import com.iamkurtgoz.application.local.entity.ProductEntity

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ProductEntity)

    @Query("SELECT * FROM productEntity WHERE category = :category")
    suspend fun list(category: String): List<ProductEntity>
}