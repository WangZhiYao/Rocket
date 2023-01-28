package com.yizhenwind.rocket.feature.account.di

import com.yizhenwind.rocket.core.mediator.account.IAccountService
import com.yizhenwind.rocket.feature.account.di.service.AccountServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
@Module
@InstallIn(SingletonComponent::class)
object AccountModule {

    @Provides
    @Singleton
    fun provideAccountService(accountServiceImpl: AccountServiceImpl): IAccountService =
        accountServiceImpl

}