package com.yizhenwind.rocket.feature.client.ui.composite.order

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.base.BasePagingDataAdapter
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.OrderProfile
import com.yizhenwind.rocket.feature.client.databinding.ItemClientOrderBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/1/23
 */
class ClientOrderAdapter :
    BasePagingDataAdapter<OrderProfile, ClientOrderViewHolder>(ORDER_PROFILE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientOrderViewHolder =
        ClientOrderViewHolder(parent.viewBinding(ItemClientOrderBinding::inflate)).apply {
            onItemClickListener = { orderProfile ->
                this@ClientOrderAdapter.onItemClickListener?.invoke(orderProfile)
            }
        }

    companion object {

        private val ORDER_PROFILE_COMPARATOR =
            object : DiffUtil.ItemCallback<OrderProfile>() {

                override fun areItemsTheSame(
                    oldItem: OrderProfile,
                    newItem: OrderProfile
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: OrderProfile,
                    newItem: OrderProfile
                ): Boolean =
                    oldItem == newItem

            }
    }
}