package com.yizhenwind.rocket.domain.order

import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.model.OrderProfile
import com.yizhenwind.rocket.data.order.OrderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class ObserveOrderProfileListByCharacterIdUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(characterId: Long): Flow<List<OrderProfile>> =
        orderRepository.observeOrderProfileListByCharacterId(characterId).flowOn(dispatcher)

}