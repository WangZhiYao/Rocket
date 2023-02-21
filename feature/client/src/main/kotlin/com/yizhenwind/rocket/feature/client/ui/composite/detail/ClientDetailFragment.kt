package com.yizhenwind.rocket.feature.client.ui.composite.detail

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.ext.formatDate
import com.yizhenwind.rocket.core.common.util.ClipboardHelper
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.fragmentArgs
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.FragmentClientDetailBinding
import com.yizhenwind.rocket.feature.client.ui.composite.ClientCompositeViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
@AndroidEntryPoint
class ClientDetailFragment :
    BaseFragment<FragmentClientDetailBinding>(FragmentClientDetailBinding::inflate),
    IMVIHost<ClientDetailViewState, ClientDetailSideEffect> {

    private val activityViewModel by activityViewModels<ClientCompositeViewModel>()
    private val viewModel by viewModels<ClientDetailViewModel>()
    private val args by fragmentArgs<ClientDetailArgs>()

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
            observeClientById(args.clientId)
        }
    }

    override fun initView() {
        binding.apply {
            clClientDetailContact.setOnLongClickListener {
                if (ClipboardHelper.copy(requireContext(), viewModel.contact)) {
                    root.showSnack(R.string.client_detail_copy_contact_to_clipboard_success)
                    true
                } else {
                    false
                }
            }
        }
    }

    override suspend fun render(state: ClientDetailViewState) {
        state.client.apply {
            if (id != Constant.DEFAULT_ID) {
                activityViewModel.setClient(this)
                binding.apply {
                    tvClientDetailContactType.text = contactType.name
                    tvClientDetailContact.text = contact
                    tvClientDetailRemark.text = remark.ifBlank { getString(R.string.empty_remark) }
                    tvClientDetailCreateTime.text = createTime.formatDate()
                }
            }
        }
    }

    override fun handleSideEffect(sideEffect: ClientDetailSideEffect) {
        activityViewModel.showError(sideEffect.resId)
    }
}