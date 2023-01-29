package com.yizhenwind.rocket.core.framework.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.rocket.core.framework.databinding.FragmentBaseListBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/5
 */
abstract class BaseListFragment :
    BaseFragment<FragmentBaseListBinding>(FragmentBaseListBinding::inflate) {

    protected open val layoutManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(requireContext())
    protected abstract val adapter: RecyclerView.Adapter<*>

    override fun init() {
        initData()
        initView()
    }

    override fun initView() {
        binding.rvList.apply {
            layoutManager = this@BaseListFragment.layoutManager
            adapter = this@BaseListFragment.adapter
        }
    }

    override fun onDestroyView() {
        binding.rvList.adapter = null
        super.onDestroyView()
    }
}