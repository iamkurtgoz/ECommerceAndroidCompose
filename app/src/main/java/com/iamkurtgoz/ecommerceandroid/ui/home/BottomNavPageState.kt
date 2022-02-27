package com.iamkurtgoz.ecommerceandroid.ui.home

import androidx.annotation.StringRes
import com.iamkurtgoz.ecommerceandroid.R
import com.iamkurtgoz.ecommerceandroid.ui.home.basket.BasketScreenNavigator
import com.iamkurtgoz.ecommerceandroid.ui.home.flow.FlowScreenNavigator

sealed class BottomNavPageState(@StringRes var titleResource: Int, var icon: Int, var route: String){
    object FlowScreen : BottomNavPageState(R.string.flow_screen_title, R.drawable.ic_flow, FlowScreenNavigator.screenName)
    object BasketScreen: BottomNavPageState(R.string.basket_screen_title, R.drawable.ic_basket, BasketScreenNavigator.screenName)
}