package com.yizhenwind.rocket.ui.client

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.ClientProfile

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
data class ClientProfileViewState(
    val clientProfileList: List<ClientProfile> = emptyList()
) : IViewState