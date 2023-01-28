package com.yizhenwind.rocket.feature.contacttype.ui

import androidx.annotation.StringRes

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
sealed class ContactTypeSideEffect {

    data class ShowError(@StringRes val resId: Int) : ContactTypeSideEffect()

    object HideError : ContactTypeSideEffect()

    data class ShowSnake(@StringRes val resId: Int) : ContactTypeSideEffect()

}