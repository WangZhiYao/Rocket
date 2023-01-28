package com.yizhenwind.rocket.feature.client.ui.composite.character

import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
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
class ClientCharacterFragment : BaseListFragment(), IMVIHost<ClientCharacterViewState, Nothing> {

    private val viewModel by viewModels<ClientCharacterViewModel>()
    private val args by fragmentArgs<ClientCharacterArgs>()

    override val adapter = ClientCharacterAdapter()

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render)
            observeCharacterProfileByClientId(args.clientId)
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

    override suspend fun render(state: ClientCharacterViewState) {
        adapter.submitList(state.characterProfileList)
    }

}