package com.yizhenwind.rocket.core.framework.ui

import com.yizhenwind.rocket.core.framework.R
import com.yizhenwind.rocket.core.framework.databinding.ItemAccountProfileBinding
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.model.AccountProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class AccountProfileViewHolder(private val binding: ItemAccountProfileBinding) :
    BaseViewHolder<AccountProfile>(binding.root) {

    var onActionClickListener: ((AccountProfile) -> Unit)? = null

    override fun bind(item: AccountProfile) {
        binding.apply {
            item.apply {
                tvAccountUsername.text = username
                tvAccountProfile.apply {
                    text = StringBuilder()
                        .append(
                            context.getString(
                                R.string.item_profile_character_count,
                                characterCount
                            )
                        )
                        .append(" ")
                        .append(
                            context.getString(
                                R.string.item_profile_order_count,
                                orderCount
                            )
                        )
                        .toString()
                }
            }

            root.setThrottleClickListener { onItemClickListener?.invoke(item) }
        }
    }
}