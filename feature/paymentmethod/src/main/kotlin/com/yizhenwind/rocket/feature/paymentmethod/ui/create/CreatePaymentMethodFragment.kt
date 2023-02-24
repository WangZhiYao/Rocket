package com.yizhenwind.rocket.feature.paymentmethod.ui.create

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.paymentmethod.databinding.FragmentCreatePaymentMethodBinding
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/2/24
 */
@AndroidEntryPoint
class CreatePaymentMethodFragment :
    BaseFragment<FragmentCreatePaymentMethodBinding>(FragmentCreatePaymentMethodBinding::inflate),
    IMVIHost<CreatePaymentMethodViewState, CreatePaymentMethodSideEffect> {

    private val viewModel by viewModels<CreatePaymentMethodViewModel>()

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, sideEffect = ::handleSideEffect)
    }

    override fun initView() {
        binding.apply {
            tietCreatePaymentMethodName.apply {
                doAfterTextChanged { name ->
                    viewModel.onNameChanged(name?.toString())
                }
            }

            fab.setThrottleClickListener {
                viewModel.createPaymentMethod(tietCreatePaymentMethodName.text?.toString())
            }
        }
    }

    override fun handleSideEffect(sideEffect: CreatePaymentMethodSideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreatePaymentMethodSideEffect.ShowNameError ->
                    tilCreatePaymentMethodName.error = getString(sideEffect.resId)
                CreatePaymentMethodSideEffect.HideNameError ->
                    tilCreatePaymentMethodName.error = null
                CreatePaymentMethodSideEffect.CreatePaymentSuccess ->
                    findNavController().navigateUp()
                is CreatePaymentMethodSideEffect.ShowError ->
                    root.showSnack(sideEffect.resId)
            }
        }
    }
}