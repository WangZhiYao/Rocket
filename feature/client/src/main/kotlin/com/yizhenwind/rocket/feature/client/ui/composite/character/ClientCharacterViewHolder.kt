package com.yizhenwind.rocket.feature.client.ui.composite.character

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder
import com.yizhenwind.rocket.core.model.CharacterProfile
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.ItemClientCharacterBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class ClientCharacterViewHolder(private val binding: ItemClientCharacterBinding) :
    BaseViewHolder<CharacterProfile>(binding.root) {

    var onActionClickListener: ((CharacterProfile) -> Unit)? = null

    override fun bind(item: CharacterProfile) {
        binding.apply {
            item.apply {
                tvCharacterSect.text = sect.name
                tvCharacterName.text = name
                tvCharacterZone.text = zone.name
                tvCharacterServer.text = server.name
                tvCharacterRemark.text =
                    if (remark.isNullOrBlank()) tvCharacterRemark.context.getString(
                        R.string.empty_remark
                    ) else remark
            }
            ibCharacterAction.setThrottleClickListener {
                onActionClickListener?.invoke(item)
            }
            root.setThrottleClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}