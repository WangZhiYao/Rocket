package com.yizhenwind.rocket.core.framework.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.databinding.ItemOrderProfileBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.OrderProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class OrderProfileAdapter :
    BaseListAdapter<OrderProfile, OrderProfileViewHolder>(ORDER_PROFILE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderProfileViewHolder =
        OrderProfileViewHolder(parent.viewBinding(ItemOrderProfileBinding::inflate)).apply {
            onItemClickListener = { orderProfile ->
                this@OrderProfileAdapter.onItemClickListener?.invoke(orderProfile)
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