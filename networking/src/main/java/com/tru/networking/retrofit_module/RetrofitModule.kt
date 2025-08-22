package com.tru.networking.retrofit_module

import android.content.Context

import com.google.gson.Gson
import com.pluto.plugins.network.interceptors.okhttp.PlutoOkhttpInterceptor
import com.tru.networking.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import zerobranch.androidremotedebugger.logging.NetLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

const val NET_LOGGING_INTERCEPTOR = "NET_LOGGING_INTERCEPTOR"
const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Named(NET_LOGGING_INTERCEPTOR)
    fun providesNetLoggingInterceptor() = NetLoggingInterceptor()


    @Provides
    @Named(LOGGING_INTERCEPTOR)
    @Singleton
    fun httpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor { message ->
        Timber.tag("OkHttp").d(message)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)


    @Singleton
    @Provides
    fun okhttpClient(
        @Named(LOGGING_INTERCEPTOR)
        loggingInterceptor: Interceptor,
        @Named(NET_LOGGING_INTERCEPTOR)
        netLoggingInterceptor: NetLoggingInterceptor,
    ): OkHttpClient = OkHttpClient().newBuilder().run {
        addInterceptor(loggingInterceptor)
        addInterceptor(netLoggingInterceptor)
        addInterceptor(PlutoOkhttpInterceptor)
        connectTimeout(2, TimeUnit.MINUTES)
        readTimeout(2, TimeUnit.MINUTES)
        build()
    }


    @Provides
    @Singleton
    fun provideRetrofitServices(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }
}