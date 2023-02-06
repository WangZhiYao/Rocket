package com.yizhenwind.rocket.feature.client.ui.composite.order

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.mediator.order.IOrderService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
@HiltViewModel
class ClientOrderViewModel @Inject constructor(
    private val orderService: IOrderService
) : BaseMVIViewModel<ClientOrderViewState, Nothing>() {

    override val container =
        container<ClientOrderViewState, Nothing>(ClientOrderViewState())

    fun observeOrderProfileByClientId(clientId: Long) {
        intent {
            orderService.observeOrderProfileListByClientId(clientId)
                .collect { orderProfileList ->
                    reduce {
                        state.copy(orderProfileList = orderProfileList)
                    }
                }
        }
    }

}