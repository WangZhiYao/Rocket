package com.yizhenwind.rocket.feature.account.ui.composite.detail

import android.text.method.PasswordTransformationMethod
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yizhenwind.rocket.core.authenticate.ext.authenticateForDecrypt
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.ext.formatDate
import com.yizhenwind.rocket.core.common.util.ClipboardHelper
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.fragmentArgs
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.feature.account.R
import com.yizhenwind.rocket.feature.account.databinding.FragmentAccountDetailBinding
import com.yizhenwind.rocket.feature.account.ui.composite.AccountCompositeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
@AndroidEntryPoint
class AccountDetailFragment :
    BaseFragment<FragmentAccountDetailBinding>(FragmentAccountDetailBinding::inflate),
    IMVIHost<AccountDetailViewState, AccountDetailSideEffect> {

    private val activityViewModel by activityViewModels<AccountCompositeViewModel>()
    private val viewModel by viewModels<AccountDetailViewModel>()
    private val args by fragmentArgs<AccountDetailArgs>()

    @Inject
    lateinit var clientService: IClientService

    @Inject
    lateinit var logger: ILogger

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render)
            observeAccountById(args.accountId)
        }
    }

    override fun initView() {
        binding.apply {
            viewModel.apply {
                clAccountDetailClient.setThrottleClickListener {
                    if (account.client.id != Constant.DEFAULT_ID) {
                        clientService.launchClientComposite(requireContext(), account.client.id)
                    }
                }
                clAccountDetailUsername.setOnLongClickListener {
                    if (ClipboardHelper.copy(requireContext(), account.username)) {
                        root.showSnack(R.string.account_detail_copy_username_to_clipboard_success)
                        true
                    } else {
                        false
                    }
                }
                clAccountDetailPassword.setOnLongClickListener {
                    if (passwordDecrypted) {
                        if (ClipboardHelper.copy(requireContext(), account.password)) {
                            root.showSnack(R.string.account_detail_copy_password_to_clipboard_success)
                            return@setOnLongClickListener true
                        } else {
                            return@setOnLongClickListener false
                        }
                    } else {
                        root.showSnack(R.string.error_account_detail_copy_password_to_clipboard)
                        return@setOnLongClickListener false
                    }
                }
                ibAccountDetailPasswordVisibility.setThrottleClickListener {
                    account.apply {
                        if (encrypted) {
                            lifecycleScope.launch(CoroutineExceptionHandler { _, throwable ->
                                logger.e(throwable)
                                root.showSnack(
                                    throwable.message
                                        ?: getString(R.string.error_biometric_failed)
                                )
                            }) {
                                decryptPassword(authenticateForDecrypt(iv))
                            }
                        } else {
                            observeAccountById(args.accountId)
                        }
                    }
                }
            }
        }
    }

    override suspend fun render(state: AccountDetailViewState) {
        binding.apply {
            state.apply {
                account.apply {
                    if (id != Constant.DEFAULT_ID) {
                        activityViewModel.apply {
                            setTitle(username)
                            setClientId(client.id)
                        }
                    }

                    tvAccountDetailClient.text = client.name
                    tvAccountDetailUsername.text = username

                    if (encrypted) {
                        ibAccountDetailPasswordVisibility.apply {
                            isVisible = true
                            setImageResource(R.drawable.ic_round_visibility_white_24dp)
                        }
                        tvAccountDetailPassword.apply {
                            setText(R.string.account_detail_password_placeholder)
                            transformationMethod = PasswordTransformationMethod.getInstance()
                        }
                    } else {
                        if (passwordDecrypt) {
                            ibAccountDetailPasswordVisibility.apply {
                                isVisible = true
                                setImageResource(R.drawable.ic_round_visibility_off_white_24dp)
                            }
                            tvAccountDetailPassword.apply {
                                text = password
                                transformationMethod = null
                            }
                        } else {
                            ibAccountDetailPasswordVisibility.isVisible = false
                        }
                    }
                    tvClientDetailCreateTime.text = createTime.formatDate()
                }
            }
        }
    }

    override fun handleSideEffect(sideEffect: AccountDetailSideEffect) {
        activityViewModel.showError(sideEffect.resId)
    }

}