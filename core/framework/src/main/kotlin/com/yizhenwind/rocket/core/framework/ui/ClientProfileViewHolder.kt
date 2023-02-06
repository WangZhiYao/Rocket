package com.yizhenwind.rocket.core.framework.ui

import com.yizhenwind.rocket.core.framework.R
import com.yizhenwind.rocket.core.framework.databinding.ItemClientProfileBinding
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.model.ClientProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class ClientProfileViewHolder(private val binding: ItemClientProfileBinding) :
    BaseViewHolder<ClientProfile>(binding.root) {

    var onActionClickListener: ((ClientProfile) -> Unit)? = null

    override fun bind(item: ClientProfile) {
        binding.apply {
            item.apply {
                tvClientName.text = name
                tvClientProfile.apply {
                    text = StringBuilder()
                        .append(
                            context.getString(
                                R.string.item_profile_account_count,
                                accountCount
                            )
                        )
                        .append(" ")
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

            ibClientAction.setThrottleClickListener { onActionClickListener?.invoke(item) }
            root.setThrottleClickListener { onItemClickListener?.invoke(item) }
        }
    }
}