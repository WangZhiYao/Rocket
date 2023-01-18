package com.yizhenwind.rocket.feature.client.ui.profile

import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.client.databinding.FragmentClientProfileBinding
import com.yizhenwind.rocket.feature.client.ui.create.CreateClientArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
@AndroidEntryPoint
class ClientProfileFragment :
    BaseFragment<FragmentClientProfileBinding>(FragmentClientProfileBinding::inflate),
    IMVIHost<ClientProfileViewState, Nothing> {

    private val viewModel by viewModels<ClientProfileViewModel>()
    private val adapter = ClientProfileAdapter()

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    private fun initView() {
        binding.apply {
            adapter.apply {
                onClientProfileClickListener = { clientProfile ->
                    // TODO: open client composite
                }
                onActionClickListener = { clientProfile ->
                    // TODO: open action bottom sheet
                }
                rvClientProfile.adapter = this
            }
            fabCreateClient.setThrottleClickListener {
                CreateClientArgs().launch(requireContext())
            }
        }
    }

    override suspend fun render(state: ClientProfileViewState) {
        adapter.submitData(state.clientProfileList)
    }

    override fun onDestroyView() {
        binding.rvClientProfile.adapter = null
        super.onDestroyView()
    }

}