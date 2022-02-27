package com.iamkurtgoz.ecommerceandroid.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.fonts.FontFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamkurtgoz.ecommerceandroid.ui.theme.*

@Composable
fun SearchView(
    text: MutableState<TextFieldValue>,
    placeholder: String = "",
    modifier: Modifier = Modifier,
    maxLength: Int = Int.MAX_VALUE,
    textStyle: TextStyle = typography.subtitle2,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        capitalization = KeyboardCapitalization.Characters
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    Box(
        contentAlignment = Alignment.Center
    ) {

        BasicTextField(
            value = text.value,
            onValueChange = {
                if (it.text.length <= maxLength) text.value = it.apply { it.text }
            },
            modifier = modifier
                .fillMaxWidth()
                .clip(shapes.medium)
                .background(textFieldGray)
                .padding(vertical = MaterialTheme.spacing.medium)
                .padding(horizontal = MaterialTheme.spacing.medium),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            textStyle = textStyle,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            cursorBrush = SolidColor(textColor())
        )

        if (text.value.text.isEmpty()){
            Text(
                text = placeholder,
                color = gray700,
                textAlign = TextAlign.Start,
                style = textStyle,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            )
        }

    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
private fun ShowPreview(){
    val text = remember { mutableStateOf(TextFieldValue("asd")) }
    ECommerceAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                SearchView(text = text, placeholder = "Please input your name")
            }
        }
    }
}