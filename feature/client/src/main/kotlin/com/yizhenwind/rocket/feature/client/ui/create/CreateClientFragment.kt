package com.yizhenwind.rocket.feature.client.ui.create

import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.ext.showSnackWithAction
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.FragmentCreateClientBinding
import dagger.hilt.android.AndroidEntryPoint
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

    private fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
    }

    private fun initView() {
        binding.apply {
            tietCreateClientName.doAfterTextChanged { name ->
                viewModel.onNameChanged(name?.toString())
            }

            actvCreateClientContactType.apply {
                setSimpleItems(R.array.contact_type)
                setText(resources.getStringArray(R.array.contact_type)[0], false)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onContactTypeChanged(ContactType.values()[position])
                }
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

    override suspend fun render(state: CreateClientViewState) {
        binding.apply {
            tietCreateClientContactValue.apply {
                text = null
                inputType = when (state.contactType) {
                    ContactType.QQ, ContactType.PHONE -> EditorInfo.TYPE_CLASS_NUMBER
                    ContactType.WECHAT -> EditorInfo.TYPE_CLASS_TEXT
                }
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
                    root.showSnackWithAction(
                        resId = R.string.create_client_success,
                        Snackbar.LENGTH_INDEFINITE,
                        actionResId = R.string.create_client_success_to_composite
                    ) {
                        // TODO: to client composite
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