package com.iamkurtgoz.application.local.injection

import android.content.Context
import androidx.room.Room
import com.iamkurtgoz.application.local.ProductCategoryDAO
import com.iamkurtgoz.application.local.db.ECommerceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideMarketDatabase(
        @ApplicationContext context: Context,
    ): ECommerceDatabase =
        Room.databaseBuilder(context, ECommerceDatabase::class.java, ECommerceDatabase.DB_NAME)
            .build()

    @Provides
    @Singleton
    fun provideMarketDao(
        database: ECommerceDatabase
    ): ProductCategoryDAO = database.getProductCategoryDAO()
}