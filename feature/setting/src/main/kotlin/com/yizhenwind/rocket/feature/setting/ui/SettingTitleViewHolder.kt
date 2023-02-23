package com.yizhenwind.rocket.feature.setting.ui

import com.yizhenwind.rocket.feature.setting.databinding.ItemSettingTitleBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
class SettingTitleViewHolder(private val binding: ItemSettingTitleBinding) :
    BaseSettingItemViewHolder(binding.root) {

    override fun bind(item: SettingItem) {
        if (item is SettingItem.Title) {
            binding.root.setText(item.resId)
        }
    }

}