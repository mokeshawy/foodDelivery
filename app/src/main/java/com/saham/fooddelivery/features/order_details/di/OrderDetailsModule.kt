package com.saham.fooddelivery.features.order_details.di

import com.saham.fooddelivery.features.order_details.data.repository.OrderDetailsRepositoryImpl
import com.saham.fooddelivery.features.order_details.domain.repository.OrderDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OrderDetailsModule {


    @Binds
    abstract fun provideOrderDetailsRepositoryImpl(orderDetailsRepositoryImpl: OrderDetailsRepositoryImpl): OrderDetailsRepository
}