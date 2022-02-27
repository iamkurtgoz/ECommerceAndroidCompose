package com.iamkurtgoz.application.di

import com.iamkurtgoz.application.repository.ECommerceRepository
import com.iamkurtgoz.application.repository.ECommerceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideECommerceRepository(
        eCommerceRepositoryImpl: ECommerceRepositoryImpl
    ): ECommerceRepository
}