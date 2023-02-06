package com.yizhenwind.rocket.core.framework.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.databinding.ItemCharacterProfileBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.CharacterProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class CharacterProfileAdapter :
    BaseListAdapter<CharacterProfile, CharacterProfileViewHolder>(CHARACTER_PROFILE_COMPARATOR) {

    var onActionClickListener: ((CharacterProfile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterProfileViewHolder =
        CharacterProfileViewHolder(parent.viewBinding(ItemCharacterProfileBinding::inflate)).apply {
            onItemClickListener = { characterProfile ->
                this@CharacterProfileAdapter.onItemClickListener?.invoke(characterProfile)
            }
            onActionClickListener = { characterProfile ->
                this@CharacterProfileAdapter.onActionClickListener?.invoke(characterProfile)
            }
        }

    companion object {

        private val CHARACTER_PROFILE_COMPARATOR =
            object : DiffUtil.ItemCallback<CharacterProfile>() {

                override fun areItemsTheSame(
                    oldItem: CharacterProfile,
                    newItem: CharacterProfile
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: CharacterProfile,
                    newItem: CharacterProfile
                ): Boolean =
                    oldItem == newItem

            }
    }
}