package com.yizhenwind.rocket.feature.client.ui.composite.order

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.fragmentArgs
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.client.ui.composite.character.ClientCharacterArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
@AndroidEntryPoint
class ClientOrderFragment : BaseListFragment(), IMVIHost<ClientOrderViewState, Nothing> {

    private val viewModel by viewModels<ClientOrderViewModel>()
    private val args by fragmentArgs<ClientOrderArgs>()

    override val adapter = ClientOrderAdapter()

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render)
            observeOrderProfileByClientId(args.clientId)
        }
    }

    override fun initView() {
        super.initView()
        adapter.apply {
            onItemClickListener = { orderProfile ->
                // TODO: open order composite
            }
        }
    }

    override suspend fun render(state: ClientOrderViewState) {
        adapter.submitData(state.orderProfileList)
    }
}