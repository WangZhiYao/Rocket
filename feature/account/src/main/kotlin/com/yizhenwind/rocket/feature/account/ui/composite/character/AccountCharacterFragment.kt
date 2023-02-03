package com.yizhenwind.rocket.feature.account.ui.composite.character

import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.fragmentArgs
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
@AndroidEntryPoint
class AccountCharacterFragment : BaseListFragment(), IMVIHost<AccountCharacterViewState, Nothing> {

    private val viewModel by viewModels<AccountCharacterViewModel>()
    private val args by fragmentArgs<AccountCharacterArgs>()

    override val adapter = AccountCharacterAdapter()

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render)
            observeCharacterProfileByAccountId(args.accountId)
        }
    }

    override fun initView() {
        super.initView()
        adapter.apply {
            onItemClickListener = { characterProfile ->
                // TODO: open character composite
            }
            onActionClickListener = {
                // TODO: open character bottom sheet
            }
        }
    }

    override suspend fun render(state: AccountCharacterViewState) {
        adapter.submitList(state.characterProfileList)
    }
}