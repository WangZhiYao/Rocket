package com.yizhenwind.rocket.feature.sectinternal.di

import com.yizhenwind.rocket.core.mediator.sectinternal.ISectInternalService
import com.yizhenwind.rocket.feature.sectinternal.di.service.SectInternalServiceImpl
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
object SectInternalModule {

    @Provides
    @Singleton
    fun provideSectInternalService(
        sectInternalServiceImpl: SectInternalServiceImpl
    ): ISectInternalService = sectInternalServiceImpl

}