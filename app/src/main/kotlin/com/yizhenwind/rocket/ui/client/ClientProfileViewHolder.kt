package com.yizhenwind.rocket.ui.client

import com.yizhenwind.rocket.R
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.databinding.ItemClientProfileBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
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
                                R.string.item_client_profile_account_count,
                                accountCount
                            )
                        )
                        .append(", ")
                        .append(
                            context.getString(
                                R.string.item_client_profile_character_count,
                                characterCount
                            )
                        )
                        .append(", ")
                        .append(
                            context.getString(
                                R.string.item_client_profile_order_count,
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