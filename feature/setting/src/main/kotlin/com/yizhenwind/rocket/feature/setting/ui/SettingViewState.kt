package com.yizhenwind.rocket.feature.setting.ui

import com.yizhenwind.rocket.core.framework.mvi.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
data class SettingViewState(val settingItemList: List<SettingItem> = emptyList()) : IViewState