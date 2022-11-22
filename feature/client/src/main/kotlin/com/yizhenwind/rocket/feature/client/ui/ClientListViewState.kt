package com.yizhenwind.rocket.feature.client.ui

import androidx.paging.PagingData
import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.data.model.ClientProfile

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/20
 */
data class ClientListViewState(
    val clientProfileList: PagingData<ClientProfile> = PagingData.empty()
) : IViewState