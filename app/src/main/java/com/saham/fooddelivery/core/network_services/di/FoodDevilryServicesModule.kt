package com.saham.fooddelivery.core.network_services.di

import com.saham.fooddelivery.core.network_services.FoodDevilryServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FoodDevilryServicesModule {

    @Provides
    @Singleton
    fun provideFoodDevilryServices(retrofit: Retrofit): FoodDevilryServices =
        retrofit.create(FoodDevilryServices::class.java)
}