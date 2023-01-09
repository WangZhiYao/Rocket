package com.yizhenwind.rocket.core.common.di

import com.yizhenwind.rocket.core.common.logger.ILogger
import com.yizhenwind.rocket.core.common.logger.impl.timber.TimberLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/9
 */
@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun providerLogger(): ILogger = TimberLogger()

}