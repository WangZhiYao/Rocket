package com.yizhenwind.rocket.core.framework.base

import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.rocket.core.framework.databinding.FragmentBaseMviListBinding
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.mvi.ISideEffect
import com.yizhenwind.rocket.core.framework.mvi.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/5
 */
abstract class BaseMVIListFragment<STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    BaseFragment<FragmentBaseMviListBinding>(FragmentBaseMviListBinding::inflate),
    IMVIHost<STATE, SIDE_EFFECT> {

    override fun init() {
        initView()
    }

    protected open fun initView() {
        binding.rvList.apply {
            layoutManager = this@BaseMVIListFragment.getLayoutManager()
            adapter = this@BaseMVIListFragment.getAdapter()
        }
    }

    abstract fun getLayoutManager(): RecyclerView.LayoutManager

    abstract fun getAdapter(): RecyclerView.Adapter<*>

    override fun onDestroyView() {
        binding.rvList.adapter = null
        super.onDestroyView()
    }
}