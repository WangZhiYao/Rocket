package com.yizhenwind.rocket.feature.client.ui.composite.account

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Account

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
data class ClientAccountViewState(
    val accountList: List<Account> = emptyList()
) : IViewState