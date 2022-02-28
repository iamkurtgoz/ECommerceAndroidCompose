package com.iamkurtgoz.ecommerceandroid.ui.home.flow.state

import com.iamkurtgoz.application.base.BaseViewState
import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.domain.remote.resource.FlowResource
import com.iamkurtgoz.domain.remote.resource.FlowResourceState

class FlowViewState(
    private var electronics: FlowResource<List<ProductModel>>,
    private var jewelry: FlowResource<List<ProductModel>>,
    private var menClothing: FlowResource<List<ProductModel>>,
    private val womenClothing: FlowResource<List<ProductModel>>
) : BaseViewState(electronics, jewelry, menClothing, womenClothing) {

    //ELECTRONICS
    fun getDataElectronics(): List<ProductModel>? {
        return when (electronics) {
            is FlowResource.Success<List<ProductModel>> -> (electronics as FlowResource.Success<List<ProductModel>>).data
            else -> null
        }
    }

    fun getErrorElectronics(): Throwable? {
        return when (electronics) {
            is FlowResource.Error -> (electronics as FlowResource.Error).exception
            else -> null
        }
    }

    fun getStateElectronics(): FlowResourceState {
        return when (electronics) {
            is FlowResource.Loading -> FlowResourceState.LOADING
            is FlowResource.Success<*> -> FlowResourceState.SUCCESS
            is FlowResource.Error -> FlowResourceState.ERROR
            else -> FlowResourceState.NONE
        }
    }

    //Jewelry
    fun getDataJewelry(): List<ProductModel>? {
        return when (jewelry) {
            is FlowResource.Success<List<ProductModel>> -> (jewelry as FlowResource.Success<List<ProductModel>>).data
            else -> null
        }
    }

    fun getErrorJewelry(): Throwable? {
        return when (jewelry) {
            is FlowResource.Error -> (jewelry as FlowResource.Error).exception
            else -> null
        }
    }

    fun getStateJewelry(): FlowResourceState {
        return when (jewelry) {
            is FlowResource.Loading -> FlowResourceState.LOADING
            is FlowResource.Success<*> -> FlowResourceState.SUCCESS
            is FlowResource.Error -> FlowResourceState.ERROR
            else -> FlowResourceState.NONE
        }
    }

    //MenClothing
    fun getDataMenClothing(): List<ProductModel>? {
        return when (menClothing) {
            is FlowResource.Success<List<ProductModel>> -> (menClothing as FlowResource.Success<List<ProductModel>>).data
            else -> null
        }
    }

    fun getErrorMenClothing(): Throwable? {
        return when (menClothing) {
            is FlowResource.Error -> (menClothing as FlowResource.Error).exception
            else -> null
        }
    }

    fun getStateMenClothing(): FlowResourceState {
        return when (menClothing) {
            is FlowResource.Loading -> FlowResourceState.LOADING
            is FlowResource.Success<*> -> FlowResourceState.SUCCESS
            is FlowResource.Error -> FlowResourceState.ERROR
            else -> FlowResourceState.NONE
        }
    }

    //WomenClothing
    fun getDataWomenClothing(): List<ProductModel>? {
        return when (womenClothing) {
            is FlowResource.Success<List<ProductModel>> -> (womenClothing as FlowResource.Success<List<ProductModel>>).data
            else -> null
        }
    }

    fun getErrorWomenClothing(): Throwable? {
        return when (womenClothing) {
            is FlowResource.Error -> (womenClothing as FlowResource.Error).exception
            else -> null
        }
    }

    fun getStateWomenClothing(): FlowResourceState {
        return when (womenClothing) {
            is FlowResource.Loading -> FlowResourceState.LOADING
            is FlowResource.Success<*> -> FlowResourceState.SUCCESS
            is FlowResource.Error -> FlowResourceState.ERROR
            else -> FlowResourceState.NONE
        }
    }
}