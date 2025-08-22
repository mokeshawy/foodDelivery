package com.tru.ui_component.coil_component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.tru.ui_component.R


@Composable
fun SubcomposeAsyncImageComponent(
    imageUrl: String?,
    @DrawableRes errorPlaceholder: Int = R.drawable.ic_vector_error,
    contentScale: ContentScale = ContentScale.Fit,
    circularProgressPadding: Dp = 5.dp,
    circularProgressColor: Color = MaterialTheme.colorScheme.secondary,
    modifier: Modifier = Modifier,
) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build()

    SubcomposeAsyncImage(
        model = imageRequest,
        contentDescription = "Image Resource",
        contentScale = contentScale,
        modifier = modifier,
        loading = {
            CircularProgressIndicator(
                color = circularProgressColor,
                modifier = modifier.padding(circularProgressPadding),
            )
        },
        error = {
            Image(
                painter = painterResource(errorPlaceholder),
                contentDescription = "Loaded image",
                modifier = modifier.fillMaxSize()
            )
        }
    )
}