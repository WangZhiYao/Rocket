package com.yizhenwind.rocket.feature.client.ui.profile

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.ItemClientProfileBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
class ClientProfileViewHolder(private val binding: ItemClientProfileBinding) :
    BaseViewHolder<ClientProfile>(binding.root) {

    var onAddCharacterClickListener: ((ClientProfile) -> Unit)? = null

    var onCreateOrderClickListener: ((ClientProfile) -> Unit)? = null

    override fun bind(item: ClientProfile) {
        binding.apply {
            item.apply {
                tvClientName.text = name
                tvClientProfile.apply {
                    text = StringBuilder().apply {
                        if (characterCount > 0) {
                            append(
                                context.getString(
                                    R.string.item_client_profile_character_count,
                                    characterCount
                                )
                            )
                        } else {
                            append(context.getString(R.string.item_client_profile_no_character_yet))
                        }
                        append(",")
                        if (orderCount > 0) {
                            append(
                                context.getString(
                                    R.string.item_client_profile_order_count,
                                    orderCount
                                )
                            )
                        } else {
                            append(context.getString(R.string.item_client_profile_no_order_yet))
                        }
                    }
                        .toString()
                }

                btnClientAddCharacter.setThrottleClickListener {
                    onAddCharacterClickListener?.invoke(this)
                }

                btnClientCreateOrder.setOnClickListener {
                    onCreateOrderClickListener?.invoke(this)
                }
            }
        }
    }
}