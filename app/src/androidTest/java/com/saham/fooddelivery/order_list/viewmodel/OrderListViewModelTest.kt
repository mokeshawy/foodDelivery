package com.saham.fooddelivery.order_list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.saham.fooddelivery.features.orders_list.domain.usecase.OrdersListUseCase
import com.saham.fooddelivery.features.orders_list.presentation.viewmodel.OrderListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class OrderListViewModelTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var ordersListUseCase: OrdersListUseCase
    private lateinit var viewModel: OrderListViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = OrderListViewModel(ordersListUseCase)
    }


    @Test
    fun loadOrdersShouldUpdateStateWithData() = runTest {

        viewModel.uiStateFlow.test {

            // Act
            viewModel.sendGetOrdersListIntent()

            assert(awaitItem().isLoading)

            // Success state
            val successState = awaitItem()

            assert(!successState.isLoading)
            assert(successState.orderUiModel?.isNotEmpty() == true)
            assert(successState.orderUiModel!![0].id == 2)

            cancelAndIgnoreRemainingEvents()
        }
    }
}