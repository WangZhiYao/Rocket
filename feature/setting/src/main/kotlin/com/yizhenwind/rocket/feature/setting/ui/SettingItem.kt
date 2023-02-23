package com.yizhenwind.rocket.feature.setting.ui

import android.net.Uri
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
sealed class SettingItem {

    data class Title(@StringRes val resId: Int) : SettingItem()

    data class Item(
        @StringRes val title: Int,
        @StringRes val description: Int,
        val deepLink: Uri
    ) : SettingItem()

    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<SettingItem>() {
            override fun areItemsTheSame(oldItem: SettingItem, newItem: SettingItem): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: SettingItem, newItem: SettingItem): Boolean =
                oldItem == newItem
        }
    }
}
