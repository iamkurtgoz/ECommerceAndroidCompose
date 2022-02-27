package com.iamkurtgoz.ecommerceandroid.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.iamkurtgoz.application.base.BaseViewModel
import com.iamkurtgoz.application.base.RequestType
import com.iamkurtgoz.application.base.ShowLoadingStateModel
import com.iamkurtgoz.application.base.ViewModelState
import com.iamkurtgoz.domain.extension.isNotNull
import com.iamkurtgoz.ecommerceandroid.R
import com.iamkurtgoz.ecommerceandroid.ui.theme.shapes
import com.iamkurtgoz.ecommerceandroid.ui.theme.spacing

@Composable
fun ProgressManager(
    baseViewModel: BaseViewModel,
    onActionListener: (state: ViewModelState, type: String) -> Unit = {a, b -> },
    content: @Composable () -> Unit
) {

    /*
    navController: NavController,
    onActionError: (type: String) -> Unit = {},
    isLoadingState: Boolean = false,

     */

    val state = baseViewModel.baseState.value
    val context = LocalContext.current

    Box {

        content()

        if (state.showDialog.isNotNull()) {
            val model = state.showDialog!!
            if (model.requestType == RequestType.FOR_PROGRESS_MANAGER) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colors.background),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.White, shape = shapes.large)
                            .padding(MaterialTheme.spacing.small)
                    ) {
                        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_loading))
                        LottieAnimation(
                            composition = composition,
                            iterations = LottieConstants.IterateForever
                        )
                    }
                }
            } else if (model.requestType == RequestType.FOR_DIALOG) {
                Dialog(
                    onDismissRequest = { },
                    properties = DialogProperties(
                        dismissOnBackPress = false,
                        dismissOnClickOutside = false
                    )
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.White, shape = shapes.large)
                            .padding(MaterialTheme.spacing.small)
                    ) {
                        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_loading))
                        LottieAnimation(
                            composition = composition,
                            iterations = LottieConstants.IterateForever
                        )
                    }
                }
            }
        } else if (state.alert.isNotNull()) {
            val model = state.alert!!
            if (model.requestType == RequestType.FOR_PROGRESS_MANAGER) {
                CustomAlertWithoutDialog(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, "")
                    }
                )
            } else {
                CustomAlert(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    model.cancelable,
                    onCancelRequest = {
                        baseViewModel.showContent()
                    }, onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, "")
                    }
                )
            }
        } else if (state.warning.isNotNull()) {
            val model = state.warning!!
            if (model.requestType == RequestType.FOR_PROGRESS_MANAGER) {
                CustomAlertWithoutDialog(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, "")
                    }
                )
            } else {
                CustomAlert(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    model.cancelable,
                    onCancelRequest = {
                        baseViewModel.showContent()
                    }, onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, "")
                    }
                )
            }
        } else if (state.error.isNotNull()) {
            val model = state.error!!
            if (model.requestType == RequestType.FOR_PROGRESS_MANAGER) {
                CustomAlertWithoutDialog(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, model.type ?: "")
                    }
                )
            } else {
                CustomAlert(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    model.cancelable,
                    onCancelRequest = {
                        baseViewModel.showContent()
                    }, onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, model.type ?: "")
                    }
                )
            }
        } else if (state.empty.isNotNull()) {
            val model = state.empty!!
            if (model.requestType == RequestType.FOR_PROGRESS_MANAGER) {
                CustomAlertWithoutDialog(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, "")
                    }
                )
            } else {
                CustomAlert(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    model.cancelable,
                    onCancelRequest = {
                        baseViewModel.showContent()
                    }, onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, "")
                    }
                )
            }
        } else if (state.noConnection.isNotNull()) {
            val model = state.noConnection!!
            if (model.requestType == RequestType.FOR_PROGRESS_MANAGER) {
                CustomAlertWithoutDialog(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, "")
                    }
                )
            } else {
                CustomAlert(
                    model.title,
                    model.message,
                    context.getString(R.string.ok),
                    model.cancelable,
                    onCancelRequest = {
                        baseViewModel.showContent()
                    }, onClickListener = {
                        baseViewModel.showContent()
                        onActionListener.invoke(state, "")
                    }
                )
            }
        }
    }
}