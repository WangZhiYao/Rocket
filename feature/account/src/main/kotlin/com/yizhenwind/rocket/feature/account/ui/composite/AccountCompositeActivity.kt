package com.yizhenwind.rocket.feature.account.ui.composite

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.framework.base.BaseCompositeActivity
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.account.R
import com.yizhenwind.rocket.feature.account.ui.composite.character.AccountCharacterArgs
import com.yizhenwind.rocket.feature.account.ui.composite.detail.AccountDetailArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/2/1
 */
@AndroidEntryPoint
class AccountCompositeActivity : BaseCompositeActivity(),
    IMVIHost<AccountCompositeViewState, AccountCompositeSideEffect> {

    private val viewModel by viewModels<AccountCompositeViewModel>()
    private val args by navArgs<AccountCompositeActivityArgs>()

    override fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
    }

    override fun getTitles(): List<Int> =
        arrayListOf(
            R.string.account_composite_detail,
            R.string.account_composite_character
        )

    override fun getFragments(): List<Fragment> =
        args.run {
            arrayListOf(
                AccountDetailArgs(accountId).newInstance(),
                AccountCharacterArgs(accountId).newInstance()
            )
        }

    override suspend fun render(state: AccountCompositeViewState) {
        binding.toolbar.title = state.title
    }

    override fun handleSideEffect(sideEffect: AccountCompositeSideEffect) {
        when (sideEffect) {
            is AccountCompositeSideEffect.ShowError -> {
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
}