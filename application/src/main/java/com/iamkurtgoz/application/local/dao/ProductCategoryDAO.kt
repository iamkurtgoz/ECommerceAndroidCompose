package com.iamkurtgoz.application.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamkurtgoz.application.local.entity.ProductCategoryEntity

@Dao
interface ProductCategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ProductCategoryEntity)

    @Query("SELECT * FROM productCategoryEntity")
    suspend fun list(): List<ProductCategoryEntity>
}