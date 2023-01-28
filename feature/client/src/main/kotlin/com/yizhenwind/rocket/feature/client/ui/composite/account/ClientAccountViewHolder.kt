package com.yizhenwind.rocket.feature.client.ui.composite.account

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder
import com.yizhenwind.rocket.core.model.AccountProfile
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.ItemClientAccountBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class ClientAccountViewHolder(private val binding: ItemClientAccountBinding) :
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
                                R.string.item_account_profile_character_count,
                                characterCount
                            )
                        )
                        .append(", ")
                        .append(
                            context.getString(
                                R.string.item_account_profile_order_count,
                                orderCount
                            )
                        )
                        .toString()
                }
            }

            ibAccountAction.setThrottleClickListener { onActionClickListener?.invoke(item) }
            root.setThrottleClickListener { onItemClickListener?.invoke(item) }
        }
    }

}