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
import com.iamkurtgoz.application.local.entity.ProductCategoryEntity
import com.iamkurtgoz.application.local.entity.ProductEntity
import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.application.remote.ECommerceRemoteDataSource
import com.iamkurtgoz.domain.remote.exception.NullableException
import com.iamkurtgoz.domain.entities.ProductResponse
import com.iamkurtgoz.domain.extension.isNull
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
    private val mapperCategoriesRemoteToLocale: IMapper<String, ProductCategoryEntity>,
    private val mapperProductRemoteToModel: IMapper<ProductResponse, ProductModel>,
    private val mapperProductRemoteToLocale: IMapper<ProductResponse, ProductEntity>,
    private val mapperProductLocalToModel: IMapper<ProductEntity, ProductModel>
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
            prepare { localDataSource.listCategory().map { it.title } }
        }
    }

    override fun products(category: String): Flow<FlowResource<List<ProductModel>>> {
        return if (context.isOnline()) {
            prepare(gson, ioDispatcher) { remoteDataSource.products(category) }.mapForFlow { response ->
                response.map { mapperProductRemoteToLocale.mapToResponse(it) }.forEach { localDataSource.insertProduct(it) }
                response.map { mapperProductRemoteToModel.mapToResponse(it) }.map {
                    it.apply { it.isFavorite = localDataSource.isFavoriteProduct(it.id ?: 0) > 0 }
                }
            }
        } else {
            prepare { localDataSource.listProduct(category).map { mapperProductLocalToModel.mapToResponse(it) } }
        }
    }

    override fun getFavorites(): Flow<FlowResource<List<ProductModel>>> {
        return prepare { localDataSource.getFavorites() }.mapForFlow {
            it.map { mapperProductLocalToModel.mapToResponse(it) }
        }
    }

    override fun productFavorite(id: Int): Flow<FlowResource<ProductModel>> {
        return prepare { localDataSource.getProduct(id) }.mapForFlow {
            if (it.isNull()) throw NullableException()
            val productModel = it!!.copy(isFavorite = !it.isFavorite)
            localDataSource.updateProduct(productModel)
            mapperProductLocalToModel.mapToResponse(productModel)
        }
    }


}