package com.yizhenwind.rocket.feature.character.ui.composite

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.framework.base.BaseCompositeActivity
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.widget.MessageDialog
import com.yizhenwind.rocket.core.mediator.order.IOrderService
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.feature.character.R
import com.yizhenwind.rocket.feature.character.ui.composite.detail.CharacterDetailArgs
import com.yizhenwind.rocket.feature.character.ui.composite.order.CharacterOrderArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
@AndroidEntryPoint
class CharacterCompositeActivity : BaseCompositeActivity(),
    IMVIHost<CharacterCompositeViewState, CharacterCompositeSideEffect> {

    private val navArgs by navArgs<CharacterCompositeActivityArgs>()
    private val viewModel by viewModels<CharacterCompositeViewModel>()

    @Inject
    lateinit var orderService: IOrderService

    override fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
    }

    override fun initView() {
        super.initView()
        binding.toolbar.apply {
            inflateMenu(R.menu.menu_character_composite)
            setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.action_delete) {
                    showDeleteCharacterDialog(viewModel.character)
                    return@setOnMenuItemClickListener true
                }
                return@setOnMenuItemClickListener false
            }
        }
    }

    override fun getTitles(): List<Int> =
        arrayListOf(R.string.character_composite_detail, R.string.character_composite_order)

    override fun getFragments(): List<Fragment> =
        arrayListOf(
            CharacterDetailArgs(navArgs.characterId).newInstance(),
            CharacterOrderArgs(navArgs.characterId).newInstance()
        )

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
            PAGE_INDEX_ORDER -> {
                viewModel.character.apply {
                    orderService.launchCreateOrder(
                        this@CharacterCompositeActivity,
                        client.id,
                        account.id,
                        id
                    )
                }
            }
        }
    }

    override suspend fun render(state: CharacterCompositeViewState) {
        binding.toolbar.title = state.character.name
    }

    override fun handleSideEffect(sideEffect: CharacterCompositeSideEffect) {
        when (sideEffect) {
            is CharacterCompositeSideEffect.ShowError -> {
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
            CharacterCompositeSideEffect.DeleteCharacterSuccess -> finish()
        }
    }

    private fun showDeleteCharacterDialog(character: Character) {
        MessageDialog.Builder(supportFragmentManager)
            .setTitle(getString(R.string.dialog_delete_character_title, character.name))
            .setContent(getString(R.string.dialog_delete_character_content, character.name))
            .setOnPositiveClickListener { dialog ->
                viewModel.deleteCharacter(character)
                dialog.dismiss()
            }
            .show()
    }

    companion object {

        /**
         * 角色编辑页
         */
        private const val PAGE_INDEX_EDIT = 0

        /**
         * 订单列表
         */
        private const val PAGE_INDEX_ORDER = 1

    }
}