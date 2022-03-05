package com.iamkurtgoz.ecommerceandroid.ui.home.flow.state

import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.domain.remote.resource.FlowResource

fun flowPageCombiner(
    electronics: FlowResource<List<ProductModel>>,
    jewelry: FlowResource<List<ProductModel>>,
    menClothing: FlowResource<List<ProductModel>>,
    womenClothing: FlowResource<List<ProductModel>>
) = FlowViewState(electronics, jewelry, menClothing, womenClothing)