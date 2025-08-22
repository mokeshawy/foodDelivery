package com.saham.fooddelivery.features.orders_list.di

import com.saham.fooddelivery.features.orders_list.data.repository.OrdersListRepositoryImpl
import com.saham.fooddelivery.features.orders_list.domain.repository.OrdersListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class OrderListModule {


    @Binds
    abstract fun provideOrdersListRepositoryImpl(ordersListRepositoryImpl: OrdersListRepositoryImpl): OrdersListRepository
}