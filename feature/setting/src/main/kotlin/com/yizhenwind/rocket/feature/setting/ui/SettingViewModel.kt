package com.yizhenwind.rocket.feature.setting.ui

import com.yizhenwind.rocket.core.common.constant.DeepLink
import com.yizhenwind.rocket.core.common.util.deepLink
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.feature.setting.R
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
            val settingItemList = ArrayList<SettingItem>().apply {
                add(SettingItem.Title(R.string.item_setting_title_order))
                add(
                    SettingItem.Item(
                        R.string.item_setting_category,
                        R.string.item_setting_category_description,
                        deepLink {
                            module(DeepLink.Module.CATEGORY)
                            path(DeepLink.Path.LIST)
                        }
                    )
                )
                add(
                    SettingItem.Item(
                        R.string.item_setting_payment_method,
                        R.string.item_setting_payment_method_description,
                        deepLink {
                            module(DeepLink.Module.PAYMENT_METHOD)
                            path(DeepLink.Path.LIST)
                        }
                    )
                )
            }
            reduce {
                state.copy(settingItemList = settingItemList)
            }
        }
    }
}