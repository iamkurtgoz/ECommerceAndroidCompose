package com.iamkurtgoz.application.mapper.di

import com.iamkurtgoz.domain.mapper.IMapper
import com.iamkurtgoz.application.local.model.ProductCategoryEntity
import com.iamkurtgoz.application.mapper.MapperCategoriesRemoteToLocale
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

}