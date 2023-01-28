package com.yizhenwind.rocket.data.order

import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.rocket.core.database.mapper.OrderProfileDtoMapper
import com.yizhenwind.rocket.core.model.OrderProfile
import com.yizhenwind.rocket.data.order.source.OrderLocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class OrderRepository @Inject constructor(
    private val orderLocalSource: OrderLocalSource,
    private val orderProfileDtoMapper: OrderProfileDtoMapper
) {

    fun observeOrderProfileByClientId(clientId: Long): Flow<PagingData<OrderProfile>> =
        orderLocalSource.observeOrderProfileByClientId(clientId)
            .map { pagingData ->
                pagingData.map { orderProfileDtoMapper.map(it) }
            }

}