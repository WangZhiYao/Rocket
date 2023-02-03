package com.yizhenwind.rocket.feature.account.ui.composite.character

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.base.BaseListAdapter
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.CharacterProfile
import com.yizhenwind.rocket.feature.account.databinding.ItemAccountCharacterBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
class AccountCharacterAdapter :
    BaseListAdapter<CharacterProfile, AccountCharacterViewHolder>(CHARACTER_PROFILE_COMPARATOR) {

    var onActionClickListener: ((CharacterProfile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountCharacterViewHolder =
        AccountCharacterViewHolder(parent.viewBinding(ItemAccountCharacterBinding::inflate)).apply {
            onItemClickListener = { characterProfile ->
                this@AccountCharacterAdapter.onItemClickListener?.invoke(characterProfile)
            }
            onActionClickListener = { characterProfile ->
                this@AccountCharacterAdapter.onActionClickListener?.invoke(characterProfile)
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