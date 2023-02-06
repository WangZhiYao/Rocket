package com.yizhenwind.rocket.feature.order.di.service

import com.yizhenwind.rocket.core.mediator.order.IOrderService
import com.yizhenwind.rocket.core.model.OrderProfile
import com.yizhenwind.rocket.domain.order.ObserveOrderProfileListByCharacterIdUseCase
import com.yizhenwind.rocket.domain.order.ObserveOrderProfileListByClientIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class OrderServiceImpl @Inject constructor(
    private val observeOrderProfileListByClientIdUseCase: ObserveOrderProfileListByClientIdUseCase,
    private val observeOrderProfileListByCharacterIdUseCase: ObserveOrderProfileListByCharacterIdUseCase
) : IOrderService {

    override fun observeOrderProfileListByClientId(clientId: Long): Flow<List<OrderProfile>> =
        observeOrderProfileListByClientIdUseCase(clientId)

    override fun observeOrderProfileListByCharacterId(characterId: Long): Flow<List<OrderProfile>> =
        observeOrderProfileListByCharacterIdUseCase(characterId)

}