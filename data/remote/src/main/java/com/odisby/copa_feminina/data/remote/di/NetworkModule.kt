package com.odisby.copa_feminina.data.remote.di

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL_PT = "https://odisby.github.io/Copa_Feminina_2023/docs/pt/"
    private const val BASE_URL_EN = "https://odisby.github.io/Copa_Feminina_2023/docs/en/"

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
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        val baseUrl = getBaseUrlByLanguage()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
    }

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
        level = HttpLoggingInterceptor.Level.NONE
    }
    private fun getDeviceLanguage(): String {
        return Resources.getSystem().configuration.locales[0].language
    }

    private fun getBaseUrlByLanguage(): String {
        return getDeviceLanguage().let {
            when (it) {
                "pt" -> BASE_URL_PT
                else -> BASE_URL_EN
            }
        }
    }
}