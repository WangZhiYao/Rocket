package com.yizhenwind.rocket.feature.account.ui.composite

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.mvi.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
data class AccountCompositeViewState(
    val title: CharSequence = "",
    val clientId: Long = Constant.DEFAULT_ID
) : IViewState