package com.iamkurtgoz.application.local.dao

import androidx.room.*
import com.iamkurtgoz.application.local.entity.ProductCategoryEntity
import com.iamkurtgoz.application.local.entity.ProductEntity

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: ProductEntity)

    @Query("SELECT * FROM productEntity WHERE category = :category")
    suspend fun list(category: String): List<ProductEntity>

    @Query("SELECT * FROM productEntity WHERE id = :id")
    suspend fun get(id: Int): ProductEntity?

    @Query("SELECT * FROM productEntity WHERE isFavorite = 1")
    suspend fun getFavorites(): List<ProductEntity>

    @Query("SELECT COUNT(*) FROM productEntity WHERE id = :id AND isFavorite = 1")
    suspend fun isFavorite(id: Int): Int

    @Update
    suspend fun update(entity: ProductEntity)
}