package com.iamkurtgoz.application.mapper

import com.iamkurtgoz.domain.mapper.IMapper
import com.iamkurtgoz.application.local.model.ProductCategoryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperCategoriesRemoteToLocale @Inject constructor(): IMapper<String, ProductCategoryEntity> {
    override fun mapToResponse(model: String): ProductCategoryEntity = ProductCategoryEntity(
        uid = model.lowercase(),
        title = model
    )
}