package com.yizhenwind.rocket.core.framework.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.yizhenwind.rocket.core.framework.mvi.SideEffect
import com.yizhenwind.rocket.core.framework.mvi.ViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
abstract class BaseMVIFragment<out VB : ViewBinding, STATE : ViewState, SIDE_EFFECT : SideEffect>(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BaseFragment<VB>(inflate) {

    protected open fun render(state: STATE) {

    }

    protected open fun handleSideEffect(sideEffect: SIDE_EFFECT) {

    }
}