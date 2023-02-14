package com.yizhenwind.rocket.data.order

import com.yizhenwind.rocket.core.database.mapper.OrderMapper
import com.yizhenwind.rocket.core.database.mapper.OrderProfileDtoMapper
import com.yizhenwind.rocket.core.model.Order
import com.yizhenwind.rocket.core.model.OrderProfile
import com.yizhenwind.rocket.data.order.source.OrderLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class OrderRepository @Inject constructor(
    private val orderLocalDataSource: OrderLocalDataSource,
    private val orderProfileDtoMapper: OrderProfileDtoMapper,
    private val orderMapper: OrderMapper
) {

    fun observeOrderProfileListByClientId(clientId: Long): Flow<List<OrderProfile>> =
        orderLocalDataSource.observeOrderProfileListByClientId(clientId)
            .map { orderProfileDtoList ->
                orderProfileDtoList.map { orderProfileDto ->
                    orderProfileDtoMapper.map(
                        orderProfileDto
                    )
                }
            }

    fun observeOrderProfileListByCharacterId(characterId: Long): Flow<List<OrderProfile>> =
        orderLocalDataSource.observeOrderProfileListByCharacterId(characterId)
            .map { orderProfileDtoList ->
                orderProfileDtoList.map { orderProfileDto ->
                    orderProfileDtoMapper.map(
                        orderProfileDto
                    )
                }
            }

    fun createOrder(order: Order): Flow<Order> =
        orderLocalDataSource.createOrder(orderMapper.map(order))
            .map { id -> order.copy(id = id) }

}