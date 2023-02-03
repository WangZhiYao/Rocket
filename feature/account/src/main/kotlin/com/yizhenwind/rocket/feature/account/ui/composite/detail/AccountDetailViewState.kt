package com.yizhenwind.rocket.feature.account.ui.composite.detail

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Account

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
data class AccountDetailViewState(
    val account: Account = Account(),
    val passwordDecrypt: Boolean = false
) : IViewState