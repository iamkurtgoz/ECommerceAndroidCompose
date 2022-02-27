package com.iamkurtgoz.ecommerceandroid.ui.splash

sealed class SplashState {
    object None: SplashState()
    object GoToHomeScreen: SplashState()
}