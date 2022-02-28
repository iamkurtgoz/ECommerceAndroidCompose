package com.iamkurtgoz.application.local

import com.iamkurtgoz.application.local.dao.ProductCategoryDAO
import com.iamkurtgoz.application.local.dao.ProductDAO
import com.iamkurtgoz.application.local.entity.ProductCategoryEntity
import com.iamkurtgoz.application.local.entity.ProductEntity
import javax.inject.Inject

class ECommerceLocalDataSource @Inject constructor(
    private val productCategoryDAO: ProductCategoryDAO,
    private val productDAO: ProductDAO
) {

    //Category
    suspend fun insertCategory(entity: ProductCategoryEntity) = productCategoryDAO.insert(entity)

    suspend fun listCategory() = productCategoryDAO.list()

    //product
    suspend fun insertProduct(entity: ProductEntity) = productDAO.insert(entity)

    suspend fun listProduct(category: String) = productDAO.list(category)
}
