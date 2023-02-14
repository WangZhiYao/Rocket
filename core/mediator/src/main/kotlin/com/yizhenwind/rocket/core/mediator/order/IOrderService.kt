package com.yizhenwind.rocket.core.mediator.order

import android.content.Context
import com.yizhenwind.rocket.core.model.OrderProfile
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
interface IOrderService {

    fun observeOrderProfileListByClientId(clientId: Long): Flow<List<OrderProfile>>

    fun observeOrderProfileListByCharacterId(characterId: Long): Flow<List<OrderProfile>>

    fun launchCreateOrder(context: Context, clientId: Long, accountId: Long, characterId: Long)

}