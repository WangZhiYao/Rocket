package com.yizhenwind.rocket.feature.client.ui.profile

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ui.BaseViewHolder
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.ItemClientProfileBinding

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
                tvClientProfileName.text = name
                tvClientProfileContent.apply {
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

                    tvClientProfileRemark.text =
                        remark.ifBlank { context.getString(R.string.empty_remark) }
                }
            }

            root.setThrottleClickListener { onItemClickListener?.invoke(item) }
        }
    }
}