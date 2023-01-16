package com.yizhenwind.rocket.feature.client.ui.profile

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.feature.client.databinding.ItemClientProfileBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
class ClientProfileAdapter :
    PagingDataAdapter<ClientProfile, ClientProfileViewHolder>(CLIENT_PROFILE_COMPARATOR) {

    var onClientProfileClickListener: ((ClientProfile) -> Unit)? = null

    var onAddCharacterClickListener: ((ClientProfile) -> Unit)? = null

    var onCreateOrderClickListener: ((ClientProfile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientProfileViewHolder =
        ClientProfileViewHolder(parent.viewBinding(ItemClientProfileBinding::inflate)).apply {
            onItemClickListener = { clientProfile ->
                onClientProfileClickListener?.invoke(clientProfile)
            }
            onAddCharacterClickListener = { clientProfile ->
                this@ClientProfileAdapter.onAddCharacterClickListener?.invoke(clientProfile)
            }
            onCreateOrderClickListener = { clientProfile ->
                this@ClientProfileAdapter.onCreateOrderClickListener?.invoke(clientProfile)
            }
        }

    override fun onBindViewHolder(holder: ClientProfileViewHolder, position: Int) {
        getItem(position)?.apply { holder.bind(this) }
    }

    companion object {

        private val CLIENT_PROFILE_COMPARATOR = object : DiffUtil.ItemCallback<ClientProfile>() {

            override fun areItemsTheSame(
                oldItem: ClientProfile,
                newItem: ClientProfile
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ClientProfile,
                newItem: ClientProfile
            ): Boolean = oldItem == newItem
        }
    }
}