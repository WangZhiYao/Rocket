package com.yizhenwind.rocket.feature.client.di

import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.feature.client.di.service.ClientService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/11
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ClientModule {

    @Binds
    abstract fun bindClientService(clientService: ClientService): IClientService

}