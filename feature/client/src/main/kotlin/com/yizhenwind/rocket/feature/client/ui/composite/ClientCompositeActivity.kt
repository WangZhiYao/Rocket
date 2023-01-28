package com.yizhenwind.rocket.feature.client.ui.composite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.framework.base.BaseActivity
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.setupFragmentWithTab
import com.yizhenwind.rocket.core.framework.ext.showSnackWithAction
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.ActivityClientCompositeBinding
import com.yizhenwind.rocket.feature.client.ui.composite.account.ClientAccountArgs
import com.yizhenwind.rocket.feature.client.ui.composite.character.ClientCharacterArgs
import com.yizhenwind.rocket.feature.client.ui.composite.detail.ClientDetailArgs
import com.yizhenwind.rocket.feature.client.ui.composite.order.ClientOrderArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
@AndroidEntryPoint
class ClientCompositeActivity : BaseActivity(),
    IMVIHost<ClientCompositeViewState, ClientCompositeSideEffect> {

    private val binding by viewBindings<ActivityClientCompositeBinding>()
    private val navArgs by navArgs<ClientCompositeActivityArgs>()
    private val viewModel by viewModels<ClientCompositeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initData()
        initView()
    }

    private fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
    }

    private fun initView() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                finish()
            }
            viewPager.apply {
                setupFragmentWithTab(
                    this@ClientCompositeActivity,
                    tab,
                    arrayListOf(
                        R.string.client_composite_detail,
                        R.string.client_composite_account,
                        R.string.client_composite_character,
                        R.string.client_composite_order
                    ),
                    arrayListOf(
                        ClientDetailArgs(navArgs.clientId).newInstance(),
                        ClientAccountArgs(navArgs.clientId).newInstance(),
                        ClientCharacterArgs(navArgs.clientId).newInstance(),
                        ClientOrderArgs(navArgs.clientId).newInstance()
                    )
                )
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        fab.setImageResource(
                            when (position) {
                                PAGE_INDEX_EDIT -> R.drawable.ic_round_edit_white_24dp
                                else -> R.drawable.ic_round_add_white_24dp
                            }
                        )
                    }
                })
                fab.setThrottleClickListener {
                    when (currentItem) {
                        PAGE_INDEX_EDIT -> {
                            // TODO: open edit client
                        }
                        PAGE_INDEX_ACCOUNT -> {
                            // TODO: open add account
                        }
                        PAGE_INDEX_CHARACTER -> {
                            // TODO: open add character
                        }
                        PAGE_INDEX_ORDER -> {
                            // TODO: open create order
                        }
                    }
                }
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
                    root.showSnackWithAction(
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