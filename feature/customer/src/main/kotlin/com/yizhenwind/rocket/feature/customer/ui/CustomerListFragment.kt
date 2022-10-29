package com.yizhenwind.rocket.feature.customer.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yizhenwind.rocket.core.framework.base.BaseMVIFragment
import com.yizhenwind.rocket.feature.customer.databinding.FragmentCustomerListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

/**
 * 首页客户列表
 *
 * @author WangZhiYao
 * @since 2022/10/29
 */
@AndroidEntryPoint
class CustomerListFragment :
    BaseMVIFragment<FragmentCustomerListBinding, CustomerListViewState, CustomerListSideEffect>(
        FragmentCustomerListBinding::inflate
    ) {

    private val viewModel by viewModels<CustomerListViewModel>()
    private val adapter = CustomerAdapter()

    override fun initPage() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    private fun initView() {
        binding.rvCustomerList.adapter = adapter
    }

    override fun render(state: CustomerListViewState) {
        lifecycleScope.launch {
            adapter.submitData(state.customerList)
        }
    }

    override fun onDestroyView() {
        binding.rvCustomerList.adapter = null
        super.onDestroyView()
    }
}