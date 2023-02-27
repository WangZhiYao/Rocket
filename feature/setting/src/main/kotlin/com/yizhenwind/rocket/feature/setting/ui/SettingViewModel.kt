package com.yizhenwind.rocket.feature.setting.ui

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
@HiltViewModel
class SettingViewModel @Inject constructor() : BaseMVIViewModel<SettingViewState, Nothing>() {

    override val container =
        container<SettingViewState, Nothing>(SettingViewState())

    init {
        intent {
            val settingItemList = ArrayList<SettingItem>()
            reduce {
                state.copy(settingItemList = settingItemList)
            }
        }
    }
}