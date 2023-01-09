package com.yizhenwind.rocket.core.common.di

import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.MainDispatcher
import com.yizhenwind.rocket.core.common.logger.ILogger
import com.yizhenwind.rocket.core.common.logger.impl.timber.TimberLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun providerLogger(): ILogger = TimberLogger()

}