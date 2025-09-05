package com.saham.fooddelivery.order_list.test_di

import com.saham.fooddelivery.features.orders_list.di.OrderListModule
import com.saham.fooddelivery.features.orders_list.domain.repository.OrdersListRepository
import com.saham.fooddelivery.order_list.data.repository.TestOrderListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [OrderListModule::class]
)
abstract class TestOrderListModule {

    @Binds
    abstract fun provideTestOrderListRepositoryImpl(testOrderListRepositoryImpl: TestOrderListRepositoryImpl): OrdersListRepository
}