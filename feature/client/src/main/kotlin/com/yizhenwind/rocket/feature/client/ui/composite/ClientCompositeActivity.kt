package com.yizhenwind.rocket.feature.client.ui.composite

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseCompositeActivity
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.mediator.account.IAccountService
import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.ui.composite.account.ClientAccountArgs
import com.yizhenwind.rocket.feature.client.ui.composite.character.ClientCharacterArgs
import com.yizhenwind.rocket.feature.client.ui.composite.detail.ClientDetailArgs
import com.yizhenwind.rocket.feature.client.ui.composite.order.ClientOrderArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
@AndroidEntryPoint
class ClientCompositeActivity : BaseCompositeActivity(),
    IMVIHost<ClientCompositeViewState, ClientCompositeSideEffect> {

    private val navArgs by navArgs<ClientCompositeActivityArgs>()
    private val viewModel by viewModels<ClientCompositeViewModel>()

    @Inject
    lateinit var accountService: IAccountService

    @Inject
    lateinit var characterService: ICharacterService

    override fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
    }

    override fun getTitles(): List<Int> =
        arrayListOf(
            R.string.client_composite_detail,
            R.string.client_composite_account,
            R.string.client_composite_character,
            R.string.client_composite_order
        )

    override fun getFragments(): List<Fragment> =
        navArgs.run {
            arrayListOf(
                ClientDetailArgs(clientId).newInstance(),
                ClientAccountArgs(clientId).newInstance(),
                ClientCharacterArgs(clientId).newInstance(),
                ClientOrderArgs(clientId).newInstance()
            )
        }

    override fun onPageSelected(position: Int) {
        binding.fab.setImageResource(
            when (position) {
                PAGE_INDEX_EDIT -> R.drawable.ic_round_edit_white_24dp
                else -> R.drawable.ic_round_add_white_24dp
            }
        )
    }

    override fun onActionClicked(currentIndex: Int) {
        when (currentIndex) {
            PAGE_INDEX_EDIT -> {
                // TODO: open edit client
            }
            PAGE_INDEX_ACCOUNT -> {
                accountService.launchCreateAccount(
                    this,
                    navArgs.clientId
                )
            }
            PAGE_INDEX_CHARACTER -> {
                characterService.launchCreateCharacter(
                    this,
                    navArgs.clientId,
                    Constant.DEFAULT_ID
                )
            }
            PAGE_INDEX_ORDER -> {
                // TODO: open create order
            }
        }
    }

    override suspend fun render(state: ClientCompositeViewState) {
        binding.toolbar.title = state.title
    }

    override fun handleSideEffect(sideEffect: ClientCompositeSideEffect) {
        when (sideEffect) {
            is ClientCompositeSideEffect.ShowError -> {
                binding.apply {
                    root.showSnack(
                        sideEffect.resId,
                        Snackbar.LENGTH_INDEFINITE,
                        fab,
                        R.string.error_action_close
                    ) {
                        finish()
                    }
                }
            }
        }
    }

    companion object {

        /**
         * 编辑客户信息
         */
        private const val PAGE_INDEX_EDIT = 0

        /**
         * 客户账号
         */
        private const val PAGE_INDEX_ACCOUNT = 1

        /**
         * 客户角色
         */
        private const val PAGE_INDEX_CHARACTER = 2

        /**
         * 客户订单
         */
        private const val PAGE_INDEX_ORDER = 3
    }
}