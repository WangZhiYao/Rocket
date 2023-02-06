package com.yizhenwind.rocket.feature.client.ui.composite.order

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.OrderProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
data class ClientOrderViewState(
    val orderProfileList: List<OrderProfile> = emptyList()
) : IViewState