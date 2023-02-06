package com.yizhenwind.rocket.feature.character.ui.composite

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.framework.base.BaseCompositeActivity
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.character.R
import com.yizhenwind.rocket.feature.character.ui.composite.detail.CharacterDetailArgs
import com.yizhenwind.rocket.feature.character.ui.composite.order.CharacterOrderArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

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

    override fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
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
                // TODO: open create order
            }
        }
    }

    override suspend fun render(state: CharacterCompositeViewState) {
        binding.toolbar.title = state.title
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
        }
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