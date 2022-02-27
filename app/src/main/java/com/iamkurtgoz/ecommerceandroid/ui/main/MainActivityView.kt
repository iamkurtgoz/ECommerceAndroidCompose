package com.iamkurtgoz.ecommerceandroid.ui.main

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamkurtgoz.ecommerceandroid.ui.home.HomeScreenView
import com.iamkurtgoz.ecommerceandroid.ui.home.basket.BasketScreenNavigator
import com.iamkurtgoz.ecommerceandroid.ui.home.basket.BasketScreenView
import com.iamkurtgoz.ecommerceandroid.ui.home.flow.FlowScreenNavigator
import com.iamkurtgoz.ecommerceandroid.ui.home.flow.FlowScreenView
import com.iamkurtgoz.ecommerceandroid.ui.main.screen.IScreenNavigator
import com.iamkurtgoz.ecommerceandroid.ui.splash.SplashScreenNavigator
import com.iamkurtgoz.ecommerceandroid.ui.splash.SplashScreenView

@Composable
fun MainActivityView(
    viewModel: MainViewModel,
    fullScreen: (Boolean) -> Unit,
    hideKeyboard: () -> Unit,
    activityKiller: () -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        bottomBar = { HomeScreenView(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SplashScreenNavigator.screenName
        ) {
            composable(route = SplashScreenNavigator.screenName) {
                SplashScreenView(navController = navController, activityKiller = activityKiller)
            }

            composable(route = FlowScreenNavigator.screenName) {
                Column(modifier = Modifier.padding(innerPadding)) {
                    FlowScreenView(navController = navController)
                }
            }

            composable(route = BasketScreenNavigator.screenName) {
                Column(modifier = Modifier.padding(innerPadding)) {
                    BasketScreenView(navController = navController)
                }
            }
        }

        navController.addOnDestinationChangedListener { controller: NavController, destination: NavDestination, arguments: Bundle? ->
            fullScreen.invoke(destination.route == SplashScreenNavigator.screenName)
        }
    }
}

fun NavController.navigatePopUpTo(navigator: IScreenNavigator) {
    val navOptions = NavOptions.Builder()
    navOptions.setPopUpTo(currentDestination?.route, true)
    navigate(navigator.screenName, navOptions.build())
}

fun NavController.navigatePopUpTo(route: String) {
    val navOptions = NavOptions.Builder()
    navOptions.setPopUpTo(currentDestination?.route, true)
    navigate(route, navOptions.build())
}

@Preview
@Composable
private fun MainActivityViewPreview() {
    MainActivityView(viewModel = hiltViewModel(), fullScreen = {}, hideKeyboard = {}) {

    }
}