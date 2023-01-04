package com.yizhenwind.rocket.feature.client.ui.composite.character

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.feature.client.databinding.ItemClientCharacterBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
class ClientCharacterViewHolder(private val binding: ItemClientCharacterBinding) :
    BaseViewHolder<Character>(binding.root) {

    var onCreateOrderClickListener: ((Character) -> Unit)? = null

    override fun bind(item: Character) {
        binding.apply {
            item.apply {
                tvCharacterSect.text = sect.name
                tvCharacterName.text = name
                tvCharacterZone.text = zone.name
                tvCharacterServer.text = server.name
                tvCharacterRemark.text = remark
            }

            btnCharacterCreateOrder.setThrottleClickListener {
                onCreateOrderClickListener?.invoke(item)
            }
        }
    }

}