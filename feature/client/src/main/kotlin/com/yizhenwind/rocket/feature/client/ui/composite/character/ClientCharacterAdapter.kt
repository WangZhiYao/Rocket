package com.yizhenwind.rocket.feature.client.ui.composite.character

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.base.BaseListAdapter
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.CharacterProfile
import com.yizhenwind.rocket.feature.client.databinding.ItemClientCharacterBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class ClientCharacterAdapter :
    BaseListAdapter<CharacterProfile, ClientCharacterViewHolder>(CHARACTER_PROFILE_COMPARATOR) {

    var onActionClickListener: ((CharacterProfile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientCharacterViewHolder =
        ClientCharacterViewHolder(parent.viewBinding(ItemClientCharacterBinding::inflate)).apply {
            onItemClickListener = { characterProfile ->
                this@ClientCharacterAdapter.onItemClickListener?.invoke(characterProfile)
            }
            onActionClickListener = { characterProfile ->
                this@ClientCharacterAdapter.onActionClickListener?.invoke(characterProfile)
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