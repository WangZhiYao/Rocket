package com.yizhenwind.rocket.feature.client.ui.composite.order

import com.yizhenwind.rocket.core.common.ext.formatDate
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder
import com.yizhenwind.rocket.core.model.OrderProfile
import com.yizhenwind.rocket.feature.client.databinding.ItemClientOrderBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/1/23
 */
class ClientOrderViewHolder(private val binding: ItemClientOrderBinding) :
    BaseViewHolder<OrderProfile>(binding.root) {

    override fun bind(item: OrderProfile) {
        binding.apply {
            item.apply {
                tvOrderCategoryTitle.text = subject.category.title
                tvOrderStatus.text = orderStatus.name
                tvOrderSubjectContent.text = subject.content
                tvOrderPaymentStatus.text = paymentStatus.name
                tvOrderCreateTime.text = createTime.formatDate()
            }

            root.setThrottleClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}