package com.yizhenwind.rocket.domain.order

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.OrderProfile
import com.yizhenwind.rocket.data.order.OrderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class ObserveOrderProfileListByClientIdUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(clientId: Long): Flow<List<OrderProfile>> =
        orderRepository.observeOrderProfileListByClientId(clientId)
            .flowOn(dispatcher)

}