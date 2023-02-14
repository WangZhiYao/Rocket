package com.yizhenwind.rocket.core.framework.widget

import android.view.View
import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.databinding.ItemDropDownSimpleCharacterBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.simple.SimpleCharacter

/**
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class SimpleCharacterDropDownAdapter : BaseDropDownAdapter<SimpleCharacter>() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            parent.viewBinding(ItemDropDownSimpleCharacterBinding::inflate)
        } else {
            convertView.tag as ItemDropDownSimpleCharacterBinding
        }

        getItem(position).apply {
            binding.apply {
                tvSimpleCharacterSect.text = sect.name
                tvSimpleCharacterName.text = name
                tvSimpleCharacterZone.text = zone.name
                tvSimpleCharacterServer.text = server.name
            }
        }

        return binding.root.apply { tag = binding }
    }

    override fun convertToString(item: SimpleCharacter): CharSequence = item.name

}