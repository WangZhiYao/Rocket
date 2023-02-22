package com.yizhenwind.rocket.core.framework.ui

import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.databinding.ItemCharacterProfileBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.CharacterProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class CharacterProfileAdapter :
    BaseListAdapter<CharacterProfile, CharacterProfileViewHolder>(CharacterProfile.COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterProfileViewHolder =
        CharacterProfileViewHolder(parent.viewBinding(ItemCharacterProfileBinding::inflate)).apply {
            onItemClickListener = { characterProfile ->
                this@CharacterProfileAdapter.onItemClickListener?.invoke(characterProfile)
            }
        }

}