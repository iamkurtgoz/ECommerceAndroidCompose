package com.iamkurtgoz.ecommerceandroid.ui.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.iamkurtgoz.ecommerceandroid.R
import com.iamkurtgoz.ecommerceandroid.ui.home.flow.FlowScreenNavigator
import com.iamkurtgoz.ecommerceandroid.ui.main.navigatePopUpTo
import com.iamkurtgoz.ecommerceandroid.ui.theme.screenWidth
import com.iamkurtgoz.ecommerceandroid.ui.width

@Composable
fun SplashScreenView(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController,
    activityKiller: () -> Unit
) {
    val state = viewModel.state.value
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_e_commerce))

    when(state) {
        is SplashState.GoToHomeScreen -> {
            navController.navigatePopUpTo(FlowScreenNavigator)
            viewModel.clearState()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.width(MaterialTheme.screenWidth * 0.6)
                .aspectRatio(1f)
        )
    }
}