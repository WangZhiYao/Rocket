package com.yizhenwind.rocket.core.framework.mvi

/**
 *
 * @author WangZhiYao
 * @since 2023/1/13
 */
interface IMVIHost<STATE : IViewState, SIDE_EFFECT : Any> {

    suspend fun render(state: STATE) {}

    fun handleSideEffect(sideEffect: SIDE_EFFECT) {}

}