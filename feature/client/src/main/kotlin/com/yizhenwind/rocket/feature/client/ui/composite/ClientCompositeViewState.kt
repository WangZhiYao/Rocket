package com.yizhenwind.rocket.feature.client.ui.composite

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Client

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
data class ClientCompositeViewState(val client: Client = Client()) : IViewState