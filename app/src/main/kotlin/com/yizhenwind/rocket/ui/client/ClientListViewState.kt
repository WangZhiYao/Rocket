package com.yizhenwind.rocket.ui.client

import androidx.paging.PagingData
import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.ClientProfile

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/20
 */
data class ClientListViewState(
    val clientProfileList: PagingData<ClientProfile> = PagingData.empty()
) : IViewState