package com.yizhenwind.rocket.core.framework.base

import androidx.appcompat.app.AppCompatActivity
import com.yizhenwind.rocket.core.framework.mvi.ISideEffect
import com.yizhenwind.rocket.core.framework.mvi.IViewState

/**
 * Activity 基类
 *
 * @author WangZhiYao
 * @since 2022/8/22
 */
abstract class BaseMVIActivity<STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    AppCompatActivity() {

    protected open fun render(state: STATE) {

    }

    protected open fun handleSideEffect(sideEffect: SIDE_EFFECT) {

    }
}