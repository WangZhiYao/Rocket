package com.yizhenwind.rocket.feature.paymentmethod.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.paymentmethod.R
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
@AndroidEntryPoint
class PaymentMethodListFragment : BaseListFragment(),
    IMVIHost<PaymentMethodListViewState, PaymentMethodListSideEffect> {

    private val viewModel by viewModels<PaymentMethodListViewModel>()

    override val adapter = PaymentMethodAdapter()

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
    }

    override fun initView() {
        super.initView()
        adapter.apply {
            onItemClickListener = { paymentMethod ->

            }
            onDeleteClickListener = { paymentMethod ->

            }
        }
        binding.fab.apply {
            isVisible = true
            setImageResource(R.drawable.ic_round_add_white_24dp)
            setThrottleClickListener {

            }
        }
    }

    override suspend fun render(state: PaymentMethodListViewState) {
        adapter.submitList(state.paymentMethodList)
    }

    override fun handleSideEffect(sideEffect: PaymentMethodListSideEffect) {

    }

}