package com.iamkurtgoz.application.mapper

import com.iamkurtgoz.application.local.entity.ProductEntity
import com.iamkurtgoz.domain.mapper.IMapper
import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.domain.entities.ProductResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperProductRemoteToModel @Inject constructor() : IMapper<ProductResponse, ProductModel> {
    override fun mapToResponse(model: ProductResponse): ProductModel = ProductModel(
        category = model.category,
        description = model.description,
        id = model.id,
        image = model.image,
        price = model.price,
        title = model.title,
        rate = model.rating?.rate,
        isFavorite = false
    )
}

@Singleton
class MapperProductRemoteToLocale @Inject constructor() : IMapper<ProductResponse, ProductEntity> {
    override fun mapToResponse(model: ProductResponse): ProductEntity = ProductEntity(
        category = model.category ?: "",
        description = model.description ?: "",
        id = model.id ?: 0,
        image = model.image ?: "",
        price = model.price ?: 0.0,
        title = model.title ?: "",
        rate = model.rating?.rate ?: 0.0,
        isFavorite = false
    )
}

@Singleton
class MapperProductLocaleToModel @Inject constructor() : IMapper<ProductEntity, ProductModel> {
    override fun mapToResponse(model: ProductEntity): ProductModel = ProductModel(
        category = model.category,
        description = model.description,
        id = model.id,
        image = model.image,
        price = model.price,
        title = model.title,
        rate = model.rate,
        isFavorite = model.isFavorite
    )
}