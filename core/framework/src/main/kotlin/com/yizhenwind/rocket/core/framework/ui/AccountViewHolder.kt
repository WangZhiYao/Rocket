package com.yizhenwind.rocket.core.framework.ui

import com.yizhenwind.rocket.core.common.ext.formatDate
import com.yizhenwind.rocket.core.framework.databinding.ItemAccountBinding
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.model.Account

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class AccountViewHolder(private val binding: ItemAccountBinding) :
    BaseViewHolder<Account>(binding.root) {

    override fun bind(item: Account) {
        binding.apply {
            item.apply {
                tvAccountUsername.text = username
                tvAccountCreateTime.text = createTime.formatDate()
            }

            root.setThrottleClickListener { onItemClickListener?.invoke(item) }
        }
    }
}