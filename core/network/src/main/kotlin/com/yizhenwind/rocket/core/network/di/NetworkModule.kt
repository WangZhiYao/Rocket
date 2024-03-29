package com.yizhenwind.rocket.core.network.di

import com.yizhenwind.rocket.core.logger.ILogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/20
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val DEFAULT_TIMEOUT = 10L

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: ILogger): OkHttpClient =
        OkHttpClient.Builder()
            .callTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor { message -> logger.d(message) }
                    .setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
            )
            .build()

}