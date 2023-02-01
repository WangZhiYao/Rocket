package com.yizhenwind.rocket.feature.account.ui.create

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.model.Account

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
sealed class CreateAccountSideEffect {

    data class ShowAccountError(@StringRes val resId: Int) : CreateAccountSideEffect()

    object HideAccountError : CreateAccountSideEffect()

    data class ShowPasswordError(@StringRes val resId: Int) : CreateAccountSideEffect()

    object HidePasswordError : CreateAccountSideEffect()

    data class CreateAccountSuccess(val account: Account) : CreateAccountSideEffect()

}