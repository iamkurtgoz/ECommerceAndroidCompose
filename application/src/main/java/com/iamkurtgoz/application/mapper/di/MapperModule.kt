package com.iamkurtgoz.application.mapper.di

import com.iamkurtgoz.domain.mapper.IMapper
import com.iamkurtgoz.application.local.entity.ProductCategoryEntity
import com.iamkurtgoz.application.local.entity.ProductEntity
import com.iamkurtgoz.application.mapper.MapperCategoriesRemoteToLocale
import com.iamkurtgoz.application.mapper.MapperProductLocaleToModel
import com.iamkurtgoz.application.mapper.MapperProductRemoteToLocale
import com.iamkurtgoz.application.mapper.MapperProductRemoteToModel
import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.domain.entities.ProductResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    @Singleton
    fun mapperCategoriesRemoteToLocale(): IMapper<String, ProductCategoryEntity> = MapperCategoriesRemoteToLocale()

    @Provides
    @Singleton
    fun mapperProductRemoteToModel(): IMapper<ProductResponse, ProductModel> = MapperProductRemoteToModel()

    @Provides
    @Singleton
    fun mapperProductRemoteToLocale(): IMapper<ProductResponse, ProductEntity> = MapperProductRemoteToLocale()

    @Provides
    @Singleton
    fun mapperProductLocalToModel(): IMapper<ProductEntity, ProductModel> = MapperProductLocaleToModel()

}