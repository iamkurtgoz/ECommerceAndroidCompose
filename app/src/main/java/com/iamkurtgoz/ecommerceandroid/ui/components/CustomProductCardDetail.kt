package com.iamkurtgoz.ecommerceandroid.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.domain.extension.isNotNull
import com.iamkurtgoz.domain.extension.isTrue
import com.iamkurtgoz.ecommerceandroid.ui.theme.*
import com.iamkurtgoz.ecommerceandroid.ui.width
import com.iamkurtgoz.ecommerceandroid.R

@Composable
fun CustomProductCardDetail(
    modifier: Modifier = Modifier,
    productModel: ProductModel
) {
    Card(
        elevation = 4.dp,
        shape = shapes.large,
        backgroundColor = white,
        modifier = modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        ) {

            Image(
                painter = rememberImagePainter(productModel.image),
                contentDescription = null,
                modifier = Modifier.aspectRatio(0.75f)
            )

            Text(
                text = productModel.title ?: "",
                style = productBoldTitleFont,
                color = black,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
            )

            Text(
                text = productModel.description ?: "",
                style = typography.caption,
                color = gray700,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = MaterialTheme.spacing.extraExtraSmall)
            )

            Row(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "$${(productModel.price ?: 0.0)}",
                        style = productBoldTitleFont,
                        maxLines = 1,
                        color = black,
                        overflow = TextOverflow.Ellipsis,
                    )

                    RatingBar(rating = productModel.rate?.toInt() ?: 0)
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
private fun ShowPreview(){
    val productModel = ProductModel(
        id = 1,
        title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
        price = 109.95,
        description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
        category = "men's clothing",
        image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
        rate = 3.9,
        isFavorite = true
    )
    ECommerceAndroidTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomProductCardDetail(
                modifier = Modifier,
                productModel = productModel
            )
        }
    }
}