package com.tru.ui_component.app_top_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.tru.theme.background_normal
import com.tru.theme.background_normal_hover
import com.tru.theme.black_normal


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        colors = MdawmNavigationDefaults.mdawmTopAppBarColors(),
        title = { TopAppBarTitle(title) },
        navigationIcon = navigationIcon,
        actions = actions,
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

object MdawmNavigationDefaults {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun mdawmTopAppBarColors() = TopAppBarDefaults.topAppBarColors(
        containerColor = background_normal,
        scrolledContainerColor = background_normal_hover,
        navigationIconContentColor = black_normal,
        titleContentColor = black_normal,
        actionIconContentColor = black_normal
    )
}

@Composable
fun TopAppBarTitle(title: String) {
    Text(
        text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp
        )
    )
}