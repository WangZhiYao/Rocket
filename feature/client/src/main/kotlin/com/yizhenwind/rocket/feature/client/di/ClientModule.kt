package com.yizhenwind.rocket.feature.client.di

import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.feature.client.di.serivce.ClientServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
@Module
@InstallIn(SingletonComponent::class)
object ClientModule {

    @Provides
    @Singleton
    fun provideClientService(clientServiceImpl: ClientServiceImpl): IClientService =
        clientServiceImpl

}