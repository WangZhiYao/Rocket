package com.yizhenwind.rocket.core.network.di

import com.yizhenwind.rocket.core.infra.logger.Logger
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
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: Logger): OkHttpClient =
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

    companion object {

        private const val DEFAULT_TIMEOUT = 10L

    }
}