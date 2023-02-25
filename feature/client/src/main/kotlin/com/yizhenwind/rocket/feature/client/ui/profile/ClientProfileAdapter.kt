package com.yizhenwind.rocket.feature.client.ui.profile

import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.framework.ui.BaseListAdapter
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.feature.client.databinding.ItemClientProfileBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
class ClientProfileAdapter :
    BaseListAdapter<ClientProfile, ClientProfileViewHolder>(ClientProfile.COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientProfileViewHolder =
        ClientProfileViewHolder(parent.viewBinding(ItemClientProfileBinding::inflate)).apply {
            onItemClickListener = { clientProfile ->
                this@ClientProfileAdapter.onItemClickListener?.invoke(clientProfile)
            }
        }
}