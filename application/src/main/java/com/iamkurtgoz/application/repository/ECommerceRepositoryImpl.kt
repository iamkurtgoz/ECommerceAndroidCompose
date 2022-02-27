package com.iamkurtgoz.application.repository

import android.content.Context
import com.google.gson.Gson
import com.iamkurtgoz.domain.extension.isOnline
import com.iamkurtgoz.domain.mapper.IMapper
import com.iamkurtgoz.domain.remote.resource.FlowResource
import com.iamkurtgoz.application.di.IoDispatcher
import com.iamkurtgoz.application.extensions.mapForFlow
import com.iamkurtgoz.application.extensions.prepare
import com.iamkurtgoz.application.local.ECommerceLocalDataSource
import com.iamkurtgoz.application.local.model.ProductCategoryEntity
import com.iamkurtgoz.application.remote.ECommerceRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ECommerceRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val gson: Gson,
    private val remoteDataSource: ECommerceRemoteDataSource,
    private val localDataSource: ECommerceLocalDataSource,
    private val mapperCategoriesRemoteToLocale: IMapper<String, ProductCategoryEntity>
) : ECommerceRepository {

    override fun productCategories(): Flow<FlowResource<List<String>>> {
        return if (context.isOnline()) {
            prepare(gson, ioDispatcher) { remoteDataSource.productCategories() }.mapForFlow { response ->
                response.map { mapperCategoriesRemoteToLocale.mapToResponse(it) }.forEach {
                    localDataSource.insertCategory(it)
                }
                response
            }
        } else {
            prepare { localDataSource.list().map { it.title } }
        }
    }
}