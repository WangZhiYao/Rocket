package com.yizhenwind.rocket.feature.setting.ui

import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.navigate
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
@AndroidEntryPoint
class SettingFragment : BaseListFragment(), IMVIHost<SettingViewState, Nothing> {

    private val viewModel by viewModels<SettingViewModel>()

    override val adapter = SettingItemAdapter()

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    override fun initView() {
        super.initView()
        adapter.onItemClickListener = { settingItem ->
            if (settingItem is SettingItem.Item) {
                navigate(settingItem.deepLink)
            }
        }
    }

    override suspend fun render(state: SettingViewState) {
        adapter.submitList(state.settingItemList)
    }
}