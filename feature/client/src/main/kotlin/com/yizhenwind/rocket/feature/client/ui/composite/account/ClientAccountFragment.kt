package com.yizhenwind.rocket.feature.client.ui.composite.account

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.databinding.FragmentBaseListBinding
import com.yizhenwind.rocket.core.framework.ext.fragmentArgs
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
@AndroidEntryPoint
class ClientAccountFragment : BaseListFragment(), IMVIHost<ClientAccountViewState, Nothing> {

    private val viewModel by viewModels<ClientAccountViewModel>()
    private val args by fragmentArgs<ClientAccountArgs>()

    override val adapter = ClientAccountAdapter()

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render)
            observeAccountProfileByClientId(args.clientId)
        }
    }

    override fun initView() {
        super.initView()
        adapter.apply {
            onItemClickListener = { accountProfile ->
                // TODO: open account composite
            }
            onActionClickListener = { accountProfile ->
                // TODO: open action bottom sheet
            }
        }
    }

    override suspend fun render(state: ClientAccountViewState) {
        adapter.submitList(state.accountProfileList)
    }

}