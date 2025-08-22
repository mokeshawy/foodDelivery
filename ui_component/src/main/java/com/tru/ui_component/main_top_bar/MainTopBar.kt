package com.tru.ui_component.main_top_bar

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import com.tru.ui_component.R
import com.tru.ui_component.app_top_bar.AppTopAppBar
import com.tru.ui_component.icon_wrapper.IconWrapper
import com.tru.ui_component.input_fields.clearFocusOnTouch
import com.tru.ui_component.pull_to_refresh_indicator.PullToRefreshIndicator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    isShowBottomBar: Boolean = false,
    isShowRightIcon: Boolean = false,
    isRefreshing: Boolean = false,
    isPullRefresh: Boolean = true,
    @StringRes
    title: Int = R.string.kotlinNews,
    titleText: String? = null,
    @DrawableRes
    leftIcon: Int = R.drawable.ic_vector_arrow_back,
    @DrawableRes
    rightIcon: Int = R.drawable.ic_vector_menu,
    contentAlignment: Alignment = Alignment.TopStart,
    onRefresh: () -> Unit = {},
    onRightIconClicked: () -> Unit = {},
    onBackHandler: () -> Unit = {},
    onLeftIconClicked: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val refreshState = rememberPullToRefreshState()

    BackHandler { onBackHandler() }
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .clearFocusOnTouch(),
        topBar = {
            AppTopAppBar(
                title = if (titleText.isNullOrEmpty()) {
                    stringResource(id = title)
                } else {
                    titleText
                }, navigationIcon = {
                    if (!isShowBottomBar) {
                        IconButton(onClick = { onLeftIconClicked() }) {
                            IconWrapper(size = 24.dp) {
                                Icon(
                                    painter = painterResource(id = leftIcon),
                                    contentDescription = "Menu icon",
                                )
                            }
                        }
                    }
                }, actions = {
                    if (isShowRightIcon) {
                        IconButton(onClick = dropUnlessResumed(block = onRightIconClicked)) {
                            IconWrapper(size = 24.dp) {
                                Icon(
                                    painter = painterResource(id = rightIcon),
                                    contentDescription = "Profile",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }, scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pullToRefresh(
                    state = refreshState, isRefreshing = isRefreshing, onRefresh = onRefresh
                ), contentAlignment = contentAlignment
        ) {

            content()

            if (isPullRefresh)
                PullToRefreshIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    state = refreshState
                )
        }
    }
}
