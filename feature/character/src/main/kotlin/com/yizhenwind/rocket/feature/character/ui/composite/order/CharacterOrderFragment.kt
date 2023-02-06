package com.yizhenwind.rocket.feature.character.ui.composite.order

import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.fragmentArgs
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.ui.OrderProfileAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
@AndroidEntryPoint
class CharacterOrderFragment : BaseListFragment(), IMVIHost<CharacterOrderViewState, Nothing> {

    private val viewModel by viewModels<CharacterOrderViewModel>()
    private val args by fragmentArgs<CharacterOrderArgs>()

    override val adapter = OrderProfileAdapter()

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render)
            observeOrderProfileByCharacterId(args.characterId)
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

    override suspend fun render(state: CharacterOrderViewState) {
        adapter.submitList(state.orderProfileList)
    }
}