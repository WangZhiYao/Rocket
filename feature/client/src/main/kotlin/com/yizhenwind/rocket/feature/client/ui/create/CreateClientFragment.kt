package com.yizhenwind.rocket.feature.client.ui.create

import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.FragmentCreateClientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

/**
 * 创建客户
 *
 * @author WangZhiYao
 * @since 2022/11/29
 */
@AndroidEntryPoint
class CreateClientFragment :
    BaseFragment<FragmentCreateClientBinding>(FragmentCreateClientBinding::inflate),
    IMVIHost<CreateClientViewState, CreateClientSideEffect> {

    private val viewModel by viewModels<CreateClientViewModel>()

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)

            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        contactTypeList.collect { contactTypeList ->
                            binding.apply {
                                actvCreateClientContactType.apply {
                                    contactTypeList.apply {
                                        setSimpleItems(map { it.name }.toTypedArray())
                                        setText(firstOrNull()?.name, false)
                                    }
                                }
                                tietCreateClientContactValue.text = null
                            }
                        }
                    }

                    launch {
                        contactType.collect { contactType ->
                            binding.tietCreateClientContactValue.apply {
                                text = null
                                inputType = when (contactType.id) {
                                    ContactType.QQ -> EditorInfo.TYPE_CLASS_NUMBER
                                    ContactType.WECHAT -> EditorInfo.TYPE_CLASS_TEXT
                                    else -> EditorInfo.TYPE_CLASS_TEXT
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun initView() {
        binding.apply {
            tietCreateClientName.doAfterTextChanged { name ->
                viewModel.onNameChanged(name?.toString())
            }

            actvCreateClientContactType.setOnItemClickListener { _, _, position, _ ->
                viewModel.onContactTypeChanged(position)
            }

            tietCreateClientContactValue.doAfterTextChanged { contact ->
                viewModel.onContactChanged(contact?.toString())
            }

            fab.setThrottleClickListener {
                viewModel.attemptCreateClient(
                    tietCreateClientName.text?.toString(),
                    tietCreateClientContactValue.text?.toString(),
                    tietCreateClientRemark.text?.toString()
                )
            }
        }
    }

    override fun handleSideEffect(sideEffect: CreateClientSideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreateClientSideEffect.ShowNameError ->
                    tilCreateClientName.error =
                        getString(sideEffect.resId)
                CreateClientSideEffect.HideNameError ->
                    tilCreateClientName.error = null
                is CreateClientSideEffect.ShowContactError ->
                    tilCreateClientContactValue.error = getString(sideEffect.resId)
                CreateClientSideEffect.HideContactError ->
                    tilCreateClientContactValue.error = null
                is CreateClientSideEffect.CreateClientSuccess -> {
                    resetUI()
                    root.showSnack(
                        resId = R.string.create_client_success,
                        Snackbar.LENGTH_INDEFINITE,
                        actionResId = R.string.create_client_success_to_composite
                    ) {
                        findNavController().navigate(
                            CreateClientFragmentDirections.actionCreateClientToClientComposite(
                                sideEffect.client.id
                            )
                        )
                        requireActivity().finish()
                    }
                }
                is CreateClientSideEffect.CreateClientFailure ->
                    root.showSnack(sideEffect.resId)
            }
        }
    }

    private fun resetUI() {
        binding.apply {
            tietCreateClientName.apply {
                text = null
                requestFocus()
            }
            tietCreateClientContactValue.text = null
            tietCreateClientRemark.text = null
        }
    }
}