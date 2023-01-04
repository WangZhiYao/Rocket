package com.yizhenwind.rocket.feature.client.ui.composite.character

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.feature.client.databinding.ItemClientCharacterBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
class ClientCharacterAdapter :
    ListAdapter<Character, ClientCharacterViewHolder>(CHARACTER_COMPARATOR) {

    var onCreateOrderClickListener: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientCharacterViewHolder =
        ClientCharacterViewHolder(parent.viewBinding(ItemClientCharacterBinding::inflate)).apply {
            onItemClickListener = {

            }

            onCreateOrderClickListener = { character ->
                this@ClientCharacterAdapter.onCreateOrderClickListener?.invoke(character)
            }
        }

    override fun onBindViewHolder(holder: ClientCharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }
}