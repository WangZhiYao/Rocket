package com.yizhenwind.rocket.feature.paymentmethod.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.navigate
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
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
        binding.fab.apply {
            isVisible = true
            setImageResource(R.drawable.ic_round_add_white_24dp)
            setThrottleClickListener {
                navigate(PaymentMethodListFragmentDirections.actionPaymentMethodListToCreatePaymentMethod())
            }
        }
        adapter.onDeleteClickListener = { paymentMethod ->
            viewModel.updatePaymentMethod(paymentMethod.copy(enable = false))
        }
    }

    override suspend fun render(state: PaymentMethodListViewState) {
        adapter.submitList(state.paymentMethodList)
    }

    override fun handleSideEffect(sideEffect: PaymentMethodListSideEffect) {
        binding.apply {
            when (sideEffect) {
                is PaymentMethodListSideEffect.DeletePaymentMethodSuccess ->
                    sideEffect.paymentMethod.apply {
                        root.showSnack(
                            text = getString(R.string.payment_method_delete_success, name),
                            anchorView = fab,
                            actionText = getString(R.string.payment_method_delete_revoke)
                        ) {
                            viewModel.updatePaymentMethod(copy(enable = true))
                        }
                    }
            }
        }
    }
}