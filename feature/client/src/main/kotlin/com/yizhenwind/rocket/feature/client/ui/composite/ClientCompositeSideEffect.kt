package com.yizhenwind.rocket.feature.client.ui.composite

import androidx.annotation.StringRes

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
sealed class ClientCompositeSideEffect {

    data class ShowError(@StringRes val resId: Int) : ClientCompositeSideEffect()

    object DeleteClientSuccess : ClientCompositeSideEffect()

}