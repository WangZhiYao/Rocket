package com.yizhenwind.rocket.feature.zoneserver

import com.yizhenwind.rocket.core.mediator.zoneserver.IZoneServerService
import com.yizhenwind.rocket.feature.zoneserver.service.ZoneServerServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
@Module
@InstallIn(SingletonComponent::class)
object ZoneServerModule {

    @Provides
    @Singleton
    fun provideZoneServerService(
        zoneServerServiceImpl: ZoneServerServiceImpl
    ): IZoneServerService = zoneServerServiceImpl

}