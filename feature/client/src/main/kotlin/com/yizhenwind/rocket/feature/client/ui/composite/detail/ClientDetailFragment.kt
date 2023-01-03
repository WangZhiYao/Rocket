package com.yizhenwind.rocket.feature.client.ui.composite.detail

import androidx.fragment.app.activityViewModels
import com.yizhenwind.rocket.core.framework.base.BaseMVIFragment
import com.yizhenwind.rocket.core.infra.ext.formatDate
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.FragmentClientDetailBinding
import com.yizhenwind.rocket.feature.client.ui.composite.ClientCompositeSideEffect
import com.yizhenwind.rocket.feature.client.ui.composite.ClientCompositeViewModel
import com.yizhenwind.rocket.feature.client.ui.composite.ClientCompositeViewState
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/20
 */
@AndroidEntryPoint
class ClientDetailFragment :
    BaseMVIFragment<FragmentClientDetailBinding, ClientCompositeViewState, ClientCompositeSideEffect>(
        FragmentClientDetailBinding::inflate
    ) {

    private val clientCompositeViewModel by activityViewModels<ClientCompositeViewModel>()

    override fun init() {
        initData()
    }

    private fun initData() {
        clientCompositeViewModel.observe(viewLifecycleOwner, state = ::render)
    }

    override fun render(state: ClientCompositeViewState) {
        state.client.apply {
            binding.apply {
                tltvClientDetailName.content = name
                if (contactList.isNotEmpty()) {
                    tltvClientDetailContact.content = contactList.first().value
                }
                tltvClientDetailRemark.content =
                    if (remark.isNullOrBlank()) getString(R.string.client_detail_remark_empty) else remark
                tltvClientDetailCreateTime.content = createTime.formatDate()
            }
        }
    }
}