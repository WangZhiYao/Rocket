package com.yizhenwind.rocket.feature.order.di

import com.yizhenwind.rocket.core.mediator.order.IOrderService
import com.yizhenwind.rocket.feature.order.di.service.OrderServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
@Module
@InstallIn(SingletonComponent::class)
class OrderModule {

    @Provides
    @Singleton
    fun provideOrderService(orderServiceImpl: OrderServiceImpl): IOrderService = orderServiceImpl

}