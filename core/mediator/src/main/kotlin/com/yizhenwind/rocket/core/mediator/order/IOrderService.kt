package com.yizhenwind.rocket.core.mediator.order

import androidx.paging.PagingData
import com.yizhenwind.rocket.core.model.OrderProfile
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
interface IOrderService {

    fun observeOrderProfileByClientId(clientId: Long): Flow<PagingData<OrderProfile>>

}