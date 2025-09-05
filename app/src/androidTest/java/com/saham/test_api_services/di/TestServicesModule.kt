package com.saham.test_api_services.di

import com.saham.fooddelivery.core.network_services.FoodDevilryServices
import com.saham.fooddelivery.core.network_services.di.FoodDevilryServicesModule
import com.saham.test_api_services.TestApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FoodDevilryServicesModule::class]
)
abstract class TestServicesModule {

    @Binds
    abstract fun provideTestApiService(testApiService: TestApiService): FoodDevilryServices
}