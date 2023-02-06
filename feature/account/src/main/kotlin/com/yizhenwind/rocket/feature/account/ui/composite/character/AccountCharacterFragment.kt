package com.yizhenwind.rocket.feature.account.ui.composite.character

import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.fragmentArgs
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.ui.CharacterProfileAdapter
import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
@AndroidEntryPoint
class AccountCharacterFragment : BaseListFragment(), IMVIHost<AccountCharacterViewState, Nothing> {

    private val viewModel by viewModels<AccountCharacterViewModel>()
    private val args by fragmentArgs<AccountCharacterArgs>()

    override val adapter = CharacterProfileAdapter()

    @Inject
    lateinit var characterService: ICharacterService

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
                characterService.launchCharacterComposite(requireContext(), characterProfile.id)
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