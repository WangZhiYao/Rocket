package com.yizhenwind.rocket.feature.setting.ui

import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.framework.ui.BaseListAdapter
import com.yizhenwind.rocket.feature.setting.databinding.ItemSettingItemBinding
import com.yizhenwind.rocket.feature.setting.databinding.ItemSettingTitleBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
class SettingItemAdapter :
    BaseListAdapter<SettingItem, BaseSettingItemViewHolder>(SettingItem.COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSettingItemViewHolder =
        when (viewType) {
            VIEW_TYPE_TITLE -> SettingTitleViewHolder(parent.viewBinding(ItemSettingTitleBinding::inflate))
            VIEW_TYPE_ITEM -> SettingItemViewHolder(parent.viewBinding(ItemSettingItemBinding::inflate)).apply {
                onItemClickListener = { settingItem ->
                    this@SettingItemAdapter.onItemClickListener?.invoke(settingItem)
                }
            }
            else -> throw IllegalArgumentException("unknown viewType: $viewType")
        }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is SettingItem.Title -> VIEW_TYPE_TITLE
            is SettingItem.Item -> VIEW_TYPE_ITEM
        }

    companion object {

        private const val VIEW_TYPE_TITLE = 1

        private const val VIEW_TYPE_ITEM = 2

    }
}