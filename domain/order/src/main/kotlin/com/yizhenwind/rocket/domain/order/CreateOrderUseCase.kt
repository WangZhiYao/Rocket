package com.yizhenwind.rocket.domain.order

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.Order
import com.yizhenwind.rocket.data.order.OrderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/12
 */
class CreateOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(order: Order): Flow<Order> =
        orderRepository.createOrder(order).flowOn(dispatcher)

}