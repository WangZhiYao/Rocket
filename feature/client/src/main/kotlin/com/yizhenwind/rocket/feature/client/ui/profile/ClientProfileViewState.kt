package com.yizhenwind.rocket.feature.client.ui.profile

import androidx.paging.PagingData
import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.ClientProfile

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
data class ClientProfileViewState(
    val clientProfileList: PagingData<ClientProfile> = PagingData.empty()
) : IViewState