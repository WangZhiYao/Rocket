package com.yizhenwind.rocket.feature.client.ui.composite

import com.yizhenwind.rocket.core.framework.mvi.ISideEffect

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/3
 */
sealed class ClientCompositeSideEffect : ISideEffect {

    object DeleteClientSuccess : ClientCompositeSideEffect()

    object DeleteClientFailed : ClientCompositeSideEffect()

}