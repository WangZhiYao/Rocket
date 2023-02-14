package com.yizhenwind.rocket.feature.account.ui.create

import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.authenticate.AuthenticateHelper
import com.yizhenwind.rocket.core.authenticate.ext.authenticateForEncrypt
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.account.R
import com.yizhenwind.rocket.feature.account.databinding.FragmentCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
@AndroidEntryPoint
class CreateAccountFragment :
    BaseFragment<FragmentCreateAccountBinding>(FragmentCreateAccountBinding::inflate),
    IMVIHost<CreateAccountViewState, CreateAccountSideEffect> {

    private val viewModel by viewModels<CreateAccountViewModel>()
    private val args by navArgs<CreateAccountFragmentArgs>()

    private val hasBiometricCapability: Boolean
        get() = AuthenticateHelper.hasBiometricCapability(requireContext())

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
    }

    override fun initView() {
        binding.apply {
            tietCreateAccountUsername.doAfterTextChanged { username ->
                viewModel.onUsernameChanged(username?.toString())
            }

            tietCreateAccountPassword.doAfterTextChanged { password ->
                viewModel.onPasswordChanged(password?.toString())
            }

            cbCreateAccountPasswordEncryption.isVisible = hasBiometricCapability

            fab.setThrottleClickListener {
                val account = tietCreateAccountUsername.text?.toString()
                val password = tietCreateAccountPassword.text?.toString()
                if (hasBiometricCapability && cbCreateAccountPasswordEncryption.isChecked) {
                    lifecycleScope.launch(CoroutineExceptionHandler { _, throwable ->
                        logger.e(throwable)
                        root.showSnack(
                            throwable.message
                                ?: getString(R.string.error_biometric_failed)
                        )
                    }) {
                        viewModel.createAccount(
                            args.clientId,
                            account,
                            password,
                            authenticateForEncrypt()
                        )
                    }
                } else {
                    viewModel.createAccount(
                        args.clientId,
                        account,
                        password
                    )
                }
            }
        }
    }

    override suspend fun render(state: CreateAccountViewState) {

    }

    override fun handleSideEffect(sideEffect: CreateAccountSideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreateAccountSideEffect.ShowAccountError -> tilCreateAccountUsername.error =
                    getString(sideEffect.resId)
                CreateAccountSideEffect.HideAccountError -> tilCreateAccountUsername.error = null
                is CreateAccountSideEffect.ShowPasswordError -> tilCreateAccountPassword.error =
                    getString(sideEffect.resId)
                CreateAccountSideEffect.HidePasswordError -> tilCreateAccountPassword.error = null
                is CreateAccountSideEffect.CreateAccountSuccess -> {
                    resetUI()
                    root.showSnack(
                        resId = R.string.create_account_success,
                        Snackbar.LENGTH_INDEFINITE,
                        actionResId = R.string.create_account_success_to_composite
                    ) {
                        findNavController().navigate(
                            CreateAccountFragmentDirections.actionCreateAccountToAccountComposite(
                                sideEffect.account.id
                            )
                        )
                    }
                }
            }
        }
    }

    private fun resetUI() {
        binding.apply {
            tietCreateAccountUsername.apply {
                text = null
                requestFocus()
            }
            tietCreateAccountPassword.text = null
        }
    }

}