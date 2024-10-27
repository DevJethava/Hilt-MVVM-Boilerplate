package com.devjethava.hiltboilerplate.di

import com.devjethava.hiltboilerplate.BuildConfig
import com.devjethava.hiltboilerplate.helper.ResponseInterceptor
import com.devjethava.hiltboilerplate.model.remote.RestApiService
import com.devjethava.hiltboilerplate.model.repository.ApiRepository
import com.devjethava.hiltboilerplate.model.repository.ApiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val connectTimeout: Long = 180// 20s
        val readTimeout: Long = 180 // 20s

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(logging)
        okHttpClientBuilder.addInterceptor(ResponseInterceptor())
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApiService {
        return retrofit.create(RestApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAPIRepository(api: RestApiService): ApiRepository {
        return ApiRepositoryImpl(api)
    }
}