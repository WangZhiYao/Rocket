package com.yizhenwind.rocket.feature.client.ui.create

import android.view.inputmethod.EditorInfo
import androidx.annotation.StringRes
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.framework.base.BaseMVIFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.ext.showSnackWithAction
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
    BaseMVIFragment<FragmentCreateClientBinding, CreateClientViewState, CreateClientSideEffect>(
        FragmentCreateClientBinding::inflate
    ) {

    private val viewModel by viewModels<CreateClientViewModel>()

    private val contactTypeList by lazy { resources.getStringArray(R.array.contact_type) }

    override fun init() {
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            tietCreateClientName.doAfterTextChanged { name ->
                viewModel.onNameChanged(name?.toString())
            }

            actvCreateClientContactType.apply {
                setSimpleItems(R.array.contact_type)
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

    private fun initData() {
        viewModel.observe(this, sideEffect = ::handleSideEffect)

        lifecycleScope.launch {
            viewModel.contactType
                .flowWithLifecycle(lifecycle)
                .collect {
                    val contactType = contactTypeList[it.index]
                    binding.apply {
                        actvCreateClientContactType.apply {
                            if (contactType != text?.toString()) {
                                setText(contactType, false)
                            }
                        }
                        tietCreateClientContactValue.inputType = when (it) {
                            ContactType.QQ, ContactType.PHONE -> EditorInfo.TYPE_CLASS_NUMBER
                            ContactType.WECHAT -> EditorInfo.TYPE_CLASS_TEXT
                        }
                    }
                }
        }
    }

    override fun handleSideEffect(sideEffect: CreateClientSideEffect) {
        when (sideEffect) {
            CreateClientSideEffect.ShowClientNameEmptyError ->
                showClientNameError(R.string.error_client_name_empty)

            CreateClientSideEffect.HideClientError ->
                binding.tilCreateClientName.error = null

            CreateClientSideEffect.ShowContactEmptyError ->
                showContactError(R.string.error_contact_empty)

            CreateClientSideEffect.ShowContactExistError ->
                showContactError(R.string.error_contact_exist)

            CreateClientSideEffect.HideContactError ->
                binding.tilCreateClientContactValue.error = null

            is CreateClientSideEffect.CreateClientSuccess ->
                doOnCreateClientSuccess(sideEffect.id)

            CreateClientSideEffect.CreateClientFailure ->
                binding.root.showSnack(R.string.error_create_client)
        }
    }

    private fun showClientNameError(@StringRes resId: Int) {
        binding.apply {
            tilCreateClientName.error = getString(resId)
            tietCreateClientName.requestFocus()
        }
    }

    private fun showContactError(@StringRes resId: Int) {
        binding.apply {
            tilCreateClientContactValue.error = getString(resId)
            tietCreateClientContactValue.requestFocus()
        }
    }

    private fun doOnCreateClientSuccess(clientId: Long) {
        binding.apply {
            tietCreateClientName.requestFocus()
            tietCreateClientName.text = null
            viewModel.onContactTypeChanged(ContactType.QQ)
            tietCreateClientContactValue.text = null
            tietCreateClientRemark.text = null
            root.showSnackWithAction(
                R.string.create_client_success,
                Snackbar.LENGTH_LONG,
                R.string.create_client_jump_to_detail
            ) {

            }
        }
    }
}