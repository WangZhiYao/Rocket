package com.yizhenwind.rocket.feature.client.ui.composite

import com.yizhenwind.rocket.core.common.model.Client
import com.yizhenwind.rocket.core.framework.mvi.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/3
 */
data class ClientCompositeViewState(val client: Client = Client()) : IViewState