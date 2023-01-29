package com.yizhenwind.rocket.feature.contacttype.ui.create

import androidx.annotation.StringRes

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
sealed class CreateContactTypeSideEffect {

    data class ShowError(@StringRes val resId: Int) : CreateContactTypeSideEffect()

    object HideError : CreateContactTypeSideEffect()

    object NavigationUp : CreateContactTypeSideEffect()

}