package com.yizhenwind.rocket.feature.client.ui.profile

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.ClientProfile

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
data class ClientProfileListViewState(
    val clientProfileList: List<ClientProfile> = emptyList()
) : IViewState