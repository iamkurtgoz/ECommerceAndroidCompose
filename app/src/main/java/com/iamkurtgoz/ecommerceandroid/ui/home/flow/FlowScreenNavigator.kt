package com.iamkurtgoz.ecommerceandroid.ui.home.flow

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.iamkurtgoz.domain.extension.toArrayList
import com.iamkurtgoz.ecommerceandroid.ui.main.screen.IScreenNavigator

object FlowScreenNavigator : IScreenNavigator {
    override val screenName: String = "FlowScreen"
    val categoryList: String = "categoryList"
    val routerSchema: String = "$screenName/{$categoryList}"
    var categoryListNavArgument = navArgument(categoryList){
        type = NavType.StringArrayType
    }

    data class Builder(val categoryList: List<String>) {
        fun getRouter() = "$screenName/${categoryList.toTypedArray().toString()}"
    }
}