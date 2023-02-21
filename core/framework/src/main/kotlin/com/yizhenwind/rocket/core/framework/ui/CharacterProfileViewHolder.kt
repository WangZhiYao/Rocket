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

    override fun bind(item: CharacterProfile) {
        binding.apply {
            item.apply {
                tvCharacterSect.text = sect.name
                tvCharacterName.text = name
                tvCharacterZone.text = zone.name
                tvCharacterServer.text = server.name
                tvCharacterRemark.text = remark.ifBlank {
                    tvCharacterRemark.context.getString(
                        R.string.empty_remark
                    )
                }
            }
            root.setThrottleClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}