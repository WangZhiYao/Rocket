package com.yizhenwind.rocket.feature.client.ui.profile

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.ui.ClientProfileAdapter
import com.yizhenwind.rocket.feature.client.R
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
@AndroidEntryPoint
class ClientProfileFragment : BaseListFragment(), IMVIHost<ClientProfileViewState, Nothing> {

    private val viewModel by viewModels<ClientProfileViewModel>()

    override val adapter = ClientProfileAdapter()

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    override fun initView() {
        super.initView()
        binding.apply {
            adapter.apply {
                onItemClickListener = { clientProfile ->
                    findNavController().navigate(
                        ClientProfileFragmentDirections.actionToClientComposite(
                            clientProfile.id
                        )
                    )
                }
                onActionClickListener = { clientProfile ->
                    // TODO: open action bottom sheet
                }
            }
            fab.apply {
                isVisible = true
                setImageResource(R.drawable.ic_round_add_white_24dp)
                setThrottleClickListener {
                    findNavController().navigate(
                        ClientProfileFragmentDirections.actionToCreateClient()
                    )
                }
            }
        }
    }

    override suspend fun render(state: ClientProfileViewState) {
        adapter.submitList(state.clientProfileList)
    }

}