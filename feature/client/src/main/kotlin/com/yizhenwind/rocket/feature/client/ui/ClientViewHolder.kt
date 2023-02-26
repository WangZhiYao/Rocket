package com.yizhenwind.rocket.feature.client.ui

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ui.BaseViewHolder
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.ItemClientBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class ClientViewHolder(private val binding: ItemClientBinding) :
    BaseViewHolder<Client>(binding.root) {

    override fun bind(item: Client) {
        binding.apply {
            item.apply {
                tvClientName.text = name
                tvClientContact.apply {
                    text = context.getString(
                        R.string.item_client_contact,
                        context.getString(contactType.resId),
                        contact
                    )
                }

                tvClientRemark.text =
                    remark.ifBlank { root.context.getString(R.string.empty_remark) }
            }

            root.setThrottleClickListener { onItemClickListener?.invoke(item) }
        }
    }
}