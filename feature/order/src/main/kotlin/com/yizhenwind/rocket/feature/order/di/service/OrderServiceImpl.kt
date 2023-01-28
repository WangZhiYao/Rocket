package com.yizhenwind.rocket.feature.order.di.service

import androidx.paging.PagingData
import com.yizhenwind.rocket.core.mediator.order.IOrderService
import com.yizhenwind.rocket.core.model.OrderProfile
import com.yizhenwind.rocket.domain.order.ObserveOrderProfileByClientIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class OrderServiceImpl @Inject constructor(
    private val observeOrderProfileByClientIdUseCase: ObserveOrderProfileByClientIdUseCase
) : IOrderService {

    override fun observeOrderProfileByClientId(clientId: Long): Flow<PagingData<OrderProfile>> =
        observeOrderProfileByClientIdUseCase(clientId)

}