package com.yizhenwind.rocket.feature.client.ui.create

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.model.Client

/**
 *
 * @author WangZhiYao
 * @since 2023/1/17
 */
sealed class CreateClientSideEffect {

    data class ShowNameError(@StringRes val resId: Int) : CreateClientSideEffect()

    object HideNameError : CreateClientSideEffect()

    data class ShowContactError(@StringRes val resId: Int) : CreateClientSideEffect()

    object HideContactError : CreateClientSideEffect()

    data class CreateClientSuccess(val client: Client) : CreateClientSideEffect()

    data class CreateClientFailure(@StringRes val resId: Int) : CreateClientSideEffect()

}