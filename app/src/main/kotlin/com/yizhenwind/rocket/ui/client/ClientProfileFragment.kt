package com.yizhenwind.rocket.ui.client

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yizhenwind.rocket.NavigationMainDirections
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
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

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    override fun initView() {
        super.initView()
        binding.apply {
            adapter.apply {
                onItemClickListener = { clientProfile ->
                    findNavController().navigate(
                        NavigationMainDirections.actionToClientComposite(clientProfile.id)
                    )
                }
                onActionClickListener = { clientProfile ->
                    // TODO: open action bottom sheet
                }
            }
            /*fabCreateClient.setThrottleClickListener {
                findNavController().navigate(
                    ClientProfileFragmentDirections.actionClientProfileToCreateClient()
                )
            }*/
        }
    }

    override suspend fun render(state: ClientProfileViewState) {
        adapter.submitData(state.clientProfileList)
    }

}