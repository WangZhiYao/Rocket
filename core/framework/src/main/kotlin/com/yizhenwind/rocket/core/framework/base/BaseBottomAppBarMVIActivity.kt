package com.yizhenwind.rocket.core.framework.base

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.yizhenwind.rocket.core.framework.databinding.ActivityBaseBottomAppbarBinding
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.core.framework.mvi.ISideEffect
import com.yizhenwind.rocket.core.framework.mvi.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
abstract class BaseBottomAppBarMVIActivity<STATE : IViewState, SIDE_EFFECT : ISideEffect> : BaseNavActivity() {

    protected val binding by viewBindings<ActivityBaseBottomAppbarBinding>()
    override val navController by lazy {
        binding.navHostFragment.getFragment<NavHostFragment>().navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    abstract fun init()

    protected open fun render(state: STATE) {

    }

    protected open fun handleSideEffect(sideEffect: SIDE_EFFECT) {

    }
}