package com.yizhenwind.rocket.feature.client.ui.create

import com.yizhenwind.rocket.core.framework.mvi.ISideEffect

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/29
 */
sealed class CreateClientSideEffect : ISideEffect {

    object ShowClientNameEmptyError : CreateClientSideEffect()

    object HideClientError : CreateClientSideEffect()

    object ShowContactEmptyError : CreateClientSideEffect()

    object ShowContactExistError : CreateClientSideEffect()

    object HideContactError : CreateClientSideEffect()

    data class CreateClientSuccess(val id: Long) : CreateClientSideEffect()

    object CreateClientFailure : CreateClientSideEffect()

}