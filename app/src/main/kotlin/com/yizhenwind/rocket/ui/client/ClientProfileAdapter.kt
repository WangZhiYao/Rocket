package com.yizhenwind.rocket.ui.client

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.databinding.ItemClientProfileBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/21
 */
class ClientProfileAdapter :
    PagingDataAdapter<ClientProfile, ClientProfileViewHolder>(CLIENT_COMPARATOR) {

    var onClientProfileActionClickListener: ((ClientProfile) -> Unit)? = null
    var onClientProfileClickListener: ((ClientProfile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientProfileViewHolder =
        ClientProfileViewHolder(parent.viewBinding(ItemClientProfileBinding::inflate)).apply {
            onClientProfileActionClickListener = { clientProfile ->
                this@ClientProfileAdapter.onClientProfileActionClickListener?.invoke(clientProfile)
            }
            onItemClickListener = { clientProfile ->
                this@ClientProfileAdapter.onClientProfileClickListener?.invoke(clientProfile)
            }
        }

    override fun onBindViewHolder(holder: ClientProfileViewHolder, position: Int) {
        getItem(position)?.apply { holder.bind(this) }
    }

    companion object {

        private val CLIENT_COMPARATOR = object : DiffUtil.ItemCallback<ClientProfile>() {

            override fun areItemsTheSame(oldItem: ClientProfile, newItem: ClientProfile) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ClientProfile, newItem: ClientProfile) =
                oldItem == newItem

        }
    }
}