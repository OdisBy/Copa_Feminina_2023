package com.odisby.copa_feminina.data.remote.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private val url = "https://odisby.github.io/Copa_Feminina_2023/docs/api.json"

    @Provides
    @Singleton
    fun providesOkHttpBuilder(
        interceptor: Interceptor
    ) = OkHttpClient.Builder().addInterceptor(interceptor)

    @Provides
    @Singleton
    fun providesGsonBuilder(): Gson =
        GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(url)

    @Provides
    @Singleton
    fun provideRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient.Builder,
    ): Retrofit = builder
        .client(okHttpClient.build())
        .build()

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}