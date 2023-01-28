package com.yizhenwind.rocket.feature.client.ui.composite.detail

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Client

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
data class ClientDetailViewState(val client: Client = Client()) : IViewState