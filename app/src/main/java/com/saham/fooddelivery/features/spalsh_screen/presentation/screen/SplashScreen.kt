package com.saham.fooddelivery.features.spalsh_screen.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saham.fooddelivery.R

import com.tru.theme.medium
import com.tru.theme.secondary_dark
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToOrderList: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.medium.copy(color = secondary_dark),
            modifier = modifier.fillMaxWidth()

        )
    }

    LaunchedEffect(Unit) {
        delay(1000)
        onNavigateToOrderList()
    }
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
    SplashScreen(onNavigateToOrderList = {})
}