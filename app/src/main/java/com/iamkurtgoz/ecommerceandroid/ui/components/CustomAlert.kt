package com.iamkurtgoz.ecommerceandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.iamkurtgoz.ecommerceandroid.ui.theme.*

@Composable
fun CustomAlert(
    title: String?,
    message: String,
    button: String,
    cancelable: Boolean = true,
    onCancelRequest: () -> Unit,
    onClickListener: () -> Unit
){
    Dialog(
        onDismissRequest = { onCancelRequest.invoke() },
        properties = DialogProperties(dismissOnBackPress = cancelable, dismissOnClickOutside = cancelable)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(16.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title ?: "",
                style = typography.subtitle1,
                color = textColor(),
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.medium),
                textAlign = TextAlign.Center
            )

            Text(
                text = message,
                style = typography.body2,
                color = textColorSecondary(),
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.medium),
                textAlign = TextAlign.Center
            )

            CustomButton(title = button, modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                onClickListener.invoke()
            }
        }
    }
}

@Composable
fun CustomAlertWithoutDialog(
    title: String?,
    message: String,
    button: String,
    onClickListener: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(16.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title ?: "",
                style = typography.subtitle1,
                color = textColor(),
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.medium),
                textAlign = TextAlign.Center
            )

            Text(
                text = message,
                style = typography.body2,
                color = textColorSecondary(),
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.medium),
                textAlign = TextAlign.Center
            )

            CustomButton(title = button, modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                onClickListener.invoke()
            }
        }
    }
}