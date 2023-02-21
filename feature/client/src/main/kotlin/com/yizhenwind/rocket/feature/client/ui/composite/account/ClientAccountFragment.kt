package com.yizhenwind.rocket.feature.client.ui.composite.account

import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.fragmentArgs
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.ui.AccountProfileAdapter
import com.yizhenwind.rocket.core.mediator.account.IAccountService
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
@AndroidEntryPoint
class ClientAccountFragment : BaseListFragment(), IMVIHost<ClientAccountViewState, Nothing> {

    private val viewModel by viewModels<ClientAccountViewModel>()
    private val args by fragmentArgs<ClientAccountArgs>()

    override val adapter = AccountProfileAdapter()

    @Inject
    lateinit var accountService: IAccountService

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
                accountService.launchAccountComposite(
                    requireContext(),
                    args.clientId,
                    accountProfile.id
                )
            }
        }
    }

    override suspend fun render(state: ClientAccountViewState) {
        adapter.submitList(state.accountProfileList)
    }

}