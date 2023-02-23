package com.yizhenwind.rocket.feature.setting.ui

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.feature.setting.databinding.ItemSettingItemBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
class SettingItemViewHolder(private val binding: ItemSettingItemBinding) :
    BaseSettingItemViewHolder(binding.root) {

    override fun bind(item: SettingItem) {
        if (item is SettingItem.Item) {
            binding.apply {
                item.apply {
                    tvSettingTitle.setText(title)
                    tvSettingDescription.setText(description)

                    root.setThrottleClickListener { onItemClickListener?.invoke(this) }
                }
            }
        }
    }

}