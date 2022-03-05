package com.iamkurtgoz.application.useCase

import com.iamkurtgoz.application.local.ECommerceLocalDataSource
import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.application.repository.ECommerceRepository
import com.iamkurtgoz.domain.remote.resource.FlowResource
import com.iamkurtgoz.domain.useCase.IUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductFavoriteUseCase @Inject constructor(
    private val eCommerceRepository: ECommerceRepository
): IUseCase<Int, Flow<FlowResource<ProductModel>>> {

    override fun invoke(params: Int) = eCommerceRepository.productFavorite(params)

}