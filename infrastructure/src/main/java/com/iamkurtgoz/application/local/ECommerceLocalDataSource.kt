package com.iamkurtgoz.application.local

import com.iamkurtgoz.application.local.model.ProductCategoryEntity
import javax.inject.Inject

class ECommerceLocalDataSource @Inject constructor(
    private val productCategoryDAO: ProductCategoryDAO
) {

    suspend fun insertCategory(entity: ProductCategoryEntity) = productCategoryDAO.insert(entity)

    suspend fun list() = productCategoryDAO.list()

}
