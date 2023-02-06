package com.yizhenwind.rocket.feature.character.ui.composite.order

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
class CharacterOrderViewModel @Inject constructor(
    private val orderService: IOrderService
) : BaseMVIViewModel<CharacterOrderViewState, Nothing>() {

    override val container =
        container<CharacterOrderViewState, Nothing>(CharacterOrderViewState())

    fun observeOrderProfileByCharacterId(characterId: Long) {
        intent {
            orderService.observeOrderProfileListByCharacterId(characterId)
                .collect { orderProfileList ->
                    reduce {
                        state.copy(orderProfileList = orderProfileList)
                    }
                }
        }
    }

}