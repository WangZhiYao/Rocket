package com.yizhenwind.rocket.data.order.source

import com.yizhenwind.rocket.core.database.dao.OrderDao
import com.yizhenwind.rocket.core.database.dto.OrderProfileDto
import com.yizhenwind.rocket.core.database.entity.OrderEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class OrderLocalDataSource @Inject constructor(
    private val orderDao: OrderDao
) {

    fun observeOrderProfileListByClientId(clientId: Long): Flow<List<OrderProfileDto>> =
        orderDao.observeOrderProfileListByClientId(clientId)

    fun observeOrderProfileListByCharacterId(characterId: Long): Flow<List<OrderProfileDto>> =
        orderDao.observeOrderProfileListByCharacterId(characterId)

    fun createOrder(orderEntity: OrderEntity): Flow<Long> =
        flow { emit(orderDao.insert(orderEntity)) }

}