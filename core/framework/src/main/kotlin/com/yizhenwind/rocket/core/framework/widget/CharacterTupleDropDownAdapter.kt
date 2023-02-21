package com.yizhenwind.rocket.core.framework.widget

import android.view.View
import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.databinding.ItemDropDownCharacterTupleBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.CharacterTuple

/**
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class CharacterTupleDropDownAdapter : BaseDropDownAdapter<CharacterTuple>() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            parent.viewBinding(ItemDropDownCharacterTupleBinding::inflate)
        } else {
            convertView.tag as ItemDropDownCharacterTupleBinding
        }

        getItem(position).apply {
            binding.apply {
                tvCharacterTupleSect.text = sect.name
                tvCharacterTupleName.text = name
                tvCharacterTupleZone.text = zone.name
                tvCharacterTupleServer.text = server.name
            }
        }

        return binding.root.apply { tag = binding }
    }

    override fun convertToString(item: CharacterTuple): CharSequence = item.name

}