package com.yizhenwind.rocket.feature.client.ui

import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.framework.ui.BaseListAdapter
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.feature.client.databinding.ItemClientBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
class ClientAdapter :
    BaseListAdapter<Client, ClientViewHolder>(Client.COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder =
        ClientViewHolder(parent.viewBinding(ItemClientBinding::inflate)).apply {
            onItemClickListener = { client ->
                this@ClientAdapter.onItemClickListener?.invoke(client)
            }
        }
}