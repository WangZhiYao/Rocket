package com.yizhenwind.rocket.ui.client

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.rocket.core.common.route.RouteAction
import com.yizhenwind.rocket.core.common.route.RouteModule
import com.yizhenwind.rocket.core.common.route.route
import com.yizhenwind.rocket.core.framework.R
import com.yizhenwind.rocket.core.framework.base.BaseMVIListFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.mediator.client.navigation.IClientNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 * 首页客户列表
 *
 * @author WangZhiYao
 * @since 2022/11/20
 */
@AndroidEntryPoint
class ClientListFragment : BaseMVIListFragment<ClientListViewState, ClientListSideEffect>() {

    private val viewModel by viewModels<ClientListViewModel>()
    private val adapter by lazy { ClientProfileAdapter() }

    @Inject
    lateinit var clientNavigation: IClientNavigation

    override fun init() {
        initData()
        super.init()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    override fun initView() {
        super.initView()
        adapter.apply {
            onClientProfileClickListener = { clientProfile ->

            }

            onClientProfileActionClickListener = { clientProfile ->

            }
        }

        binding.apply {
            fab.apply {
                setImageResource(R.drawable.ic_round_add_white_24dp)
                setThrottleClickListener {
                    clientNavigation.launch(
                        requireContext(),
                        route {
                            module(RouteModule.CLIENT)
                            action(RouteAction.CREATE)
                        }.buildUri()
                    )
                }
            }
        }
    }

    override fun render(state: ClientListViewState) {
        lifecycleScope.launch {
            adapter.submitData(state.clientProfileList)
        }
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(requireContext())

    override fun getAdapter(): RecyclerView.Adapter<*> = adapter

}