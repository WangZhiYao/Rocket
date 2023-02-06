package com.yizhenwind.rocket.core.framework.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.databinding.ItemClientProfileBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.ClientProfile

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
class ClientProfileAdapter :
    BaseListAdapter<ClientProfile, ClientProfileViewHolder>(CLIENT_PROFILE_COMPARATOR) {

    var onActionClickListener: ((ClientProfile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientProfileViewHolder =
        ClientProfileViewHolder(parent.viewBinding(ItemClientProfileBinding::inflate)).apply {
            onItemClickListener = { clientProfile ->
                this@ClientProfileAdapter.onItemClickListener?.invoke(clientProfile)
            }
            onActionClickListener = { clientProfile ->
                this@ClientProfileAdapter.onActionClickListener?.invoke(clientProfile)
            }
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