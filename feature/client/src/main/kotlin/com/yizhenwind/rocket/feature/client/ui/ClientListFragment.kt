package com.yizhenwind.rocket.feature.client.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yizhenwind.rocket.core.common.constant.DeepLink
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.navigate
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
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
class ClientListFragment : BaseListFragment(),
    IMVIHost<ClientListViewState, Nothing> {

    private val viewModel by viewModels<ClientListViewModel>()

    override val adapter = ClientAdapter()

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    override fun initView() {
        super.initView()
        binding.apply {
            adapter.apply {
                onItemClickListener = { client ->
                    findNavController().navigate(
                        ClientListFragmentDirections.actionClientListToClientComposite(
                            client.id
                        )
                    )
                }
            }
            fab.apply {
                isVisible = true
                setImageResource(R.drawable.ic_round_add_white_24dp)
                setThrottleClickListener {
                    navigate {
                        module(DeepLink.Module.CLIENT)
                        path(DeepLink.Path.CREATE)
                    }
                }
            }
        }
    }

    override suspend fun render(state: ClientListViewState) {
        adapter.submitList(state.clientList)
    }

}