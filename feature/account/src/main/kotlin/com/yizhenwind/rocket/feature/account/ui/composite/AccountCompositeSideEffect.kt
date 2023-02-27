package com.yizhenwind.rocket.feature.account.ui.composite

import androidx.annotation.StringRes

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
sealed class AccountCompositeSideEffect {

    data class ShowError(@StringRes val resId: Int) : AccountCompositeSideEffect()

    object DeleteAccountSuccess : AccountCompositeSideEffect()

}