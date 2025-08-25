package com.tru.websocket.di

import com.tru.websocket.WebSocketHelper
import com.tru.websocket.WebSocketManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class WebSocketModule {


    @Binds
    abstract fun provideWebSocketManagerImpl(webSocketManagerImpl: WebSocketManagerImpl): WebSocketHelper

}