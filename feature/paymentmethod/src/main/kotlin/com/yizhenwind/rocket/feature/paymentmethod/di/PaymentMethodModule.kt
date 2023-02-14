package com.yizhenwind.rocket.feature.paymentmethod.di

import com.yizhenwind.rocket.core.mediator.paymentmethod.IPaymentMethodService
import com.yizhenwind.rocket.feature.paymentmethod.di.service.PaymentMethodServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
@Module
@InstallIn(SingletonComponent::class)
object PaymentMethodModule {

    @Provides
    @Singleton
    fun providePaymentMethodService(
        paymentMethodServiceImpl: PaymentMethodServiceImpl
    ): IPaymentMethodService = paymentMethodServiceImpl

}