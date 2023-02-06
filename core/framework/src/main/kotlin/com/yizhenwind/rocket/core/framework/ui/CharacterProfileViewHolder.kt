package com.yizhenwind.rocket.core.framework.ui

import com.yizhenwind.rocket.core.framework.R
import com.yizhenwind.rocket.core.framework.databinding.ItemCharacterProfileBinding
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.model.CharacterProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class CharacterProfileViewHolder(private val binding: ItemCharacterProfileBinding) :
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