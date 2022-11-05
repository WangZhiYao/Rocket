package com.yizhenwind.rocket.core.framework.base

import com.yizhenwind.rocket.core.framework.mvi.SideEffect
import com.yizhenwind.rocket.core.framework.mvi.ViewState

/**
 * Activity 基类
 *
 * @author WangZhiYao
 * @since 2022/8/22
 */
abstract class BaseMVIActivity<STATE : ViewState, SIDE_EFFECT : SideEffect> :
    BaseActivity() {

    protected open fun render(state: STATE) {

    }

    protected open fun handleSideEffect(sideEffect: SIDE_EFFECT) {

    }
}