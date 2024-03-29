package com.yizhenwind.rocket.feature.account.ui.composite

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.framework.base.BaseCompositeActivity
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.widget.MessageDialog
import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.feature.account.R
import com.yizhenwind.rocket.feature.account.ui.composite.character.AccountCharacterArgs
import com.yizhenwind.rocket.feature.account.ui.composite.detail.AccountDetailArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/1
 */
@AndroidEntryPoint
class AccountCompositeActivity : BaseCompositeActivity(),
    IMVIHost<AccountCompositeViewState, AccountCompositeSideEffect> {

    private val navArgs by navArgs<AccountCompositeActivityArgs>()
    private val viewModel by viewModels<AccountCompositeViewModel>()

    @Inject
    lateinit var characterService: ICharacterService

    override fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
    }

    override fun initView() {
        super.initView()
        binding.toolbar.apply {
            inflateMenu(R.menu.menu_account_composite)
            setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.action_delete) {
                    showDeleteCharacterDialog(viewModel.account)
                    return@setOnMenuItemClickListener true
                }
                return@setOnMenuItemClickListener false
            }
        }
    }

    override fun getTitles(): List<Int> =
        arrayListOf(
            R.string.account_composite_detail,
            R.string.account_composite_character
        )

    override fun getFragments(): List<Fragment> =
        navArgs.run {
            arrayListOf(
                AccountDetailArgs(accountId).newInstance(),
                AccountCharacterArgs(accountId).newInstance()
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
            PAGE_INDEX_CHARACTER -> {
                viewModel.account.apply {
                    characterService.launchCreateCharacter(
                        this@AccountCompositeActivity,
                        client.id,
                        id
                    )
                }
            }
        }
    }

    override suspend fun render(state: AccountCompositeViewState) {
        binding.toolbar.title = state.account.username
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
            AccountCompositeSideEffect.DeleteAccountSuccess -> finish()
        }
    }

    private fun showDeleteCharacterDialog(account: Account) {
        MessageDialog.Builder(supportFragmentManager)
            .setTitle(getString(R.string.dialog_delete_account_title, account.username))
            .setContent(getString(R.string.dialog_delete_account_content, account.username))
            .setOnPositiveClickListener { dialog ->
                viewModel.deleteAccount(account)
                dialog.dismiss()
            }
            .show()
    }

    companion object {

        /**
         * 账号编辑页
         */
        private const val PAGE_INDEX_EDIT = 0

        /**
         * 角色列表
         */
        private const val PAGE_INDEX_CHARACTER = 1

    }
}