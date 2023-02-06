package com.yizhenwind.rocket.data.order.source

import com.yizhenwind.rocket.core.database.dao.OrderDao
import com.yizhenwind.rocket.core.database.dto.OrderProfileDto
import kotlinx.coroutines.flow.Flow
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

}