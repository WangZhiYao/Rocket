package com.yizhenwind.rocket.feature.client.ui

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Client

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
data class ClientListViewState(
    val clientList: List<Client> = emptyList()
) : IViewState