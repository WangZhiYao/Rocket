package com.yizhenwind.rocket.ui.client

import com.yizhenwind.rocket.R
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder
import com.yizhenwind.rocket.core.common.model.ClientProfile
import com.yizhenwind.rocket.databinding.ItemClientProfileBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/21
 */
class ClientProfileViewHolder(
    private val binding: ItemClientProfileBinding
) : BaseViewHolder<ClientProfile>(binding.root) {

    var onActionClickListener: ((ClientProfile) -> Unit)? = null

    override fun bind(item: ClientProfile) {
        binding.apply {
            item.apply {
                tvClientName.text = name
                tvClientProfile.apply {
                    text = StringBuilder().apply {
                        if (accountCount > 0) {
                            append(
                                context.getString(
                                    R.string.item_client_account_count,
                                    accountCount
                                )
                            )
                            append(",")
                            if (characterCount > 0) {
                                append(
                                    context.getString(
                                        R.string.item_client_character_count,
                                        characterCount
                                    )
                                )
                            } else {
                                append(context.getString(R.string.item_client_no_character_yet))
                            }
                            append(",")
                            if (orderCount > 0) {
                                append(
                                    context.getString(
                                        R.string.item_client_order_count,
                                        orderCount
                                    )
                                )
                            } else {
                                append(context.getString(R.string.item_client_no_order_yet))
                            }
                        } else {
                            append(context.getString(R.string.item_client_no_account_yet))
                        }
                    }
                        .toString()
                }
            }

            ibClientOption.setThrottleClickListener { onActionClickListener?.invoke(item) }

            root.setThrottleClickListener { onItemClickListener?.invoke(item) }
        }
    }
}