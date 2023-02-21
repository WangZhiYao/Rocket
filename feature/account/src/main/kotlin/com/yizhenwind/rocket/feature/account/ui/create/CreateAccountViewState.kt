package com.yizhenwind.rocket.feature.account.ui.create

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.ClientTuple

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
data class CreateAccountViewState(
    val clientTupleList: List<ClientTuple> = emptyList(),
    val clientTuple: ClientTuple = ClientTuple()
) : IViewState