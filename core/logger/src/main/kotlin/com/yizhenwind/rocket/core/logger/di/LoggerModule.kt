package com.yizhenwind.rocket.core.logger.di

import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.logger.impl.timber.TimberLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2023/1/13
 */
@Module
@InstallIn(SingletonComponent::class)
class LoggerModule {

    @Provides
    @Singleton
    fun provideLogger(): ILogger = TimberLogger()

}