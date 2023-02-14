package com.yizhenwind.rocket.core.framework.ui

import com.yizhenwind.rocket.core.common.ext.formatDate
import com.yizhenwind.rocket.core.framework.databinding.ItemOrderProfileBinding
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.model.OrderProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class OrderProfileViewHolder(private val binding: ItemOrderProfileBinding) :
    BaseViewHolder<OrderProfile>(binding.root) {

    override fun bind(item: OrderProfile) {
        binding.apply {
            item.apply {
                tvOrderCategoryTitle.text = subject.category.title
                tvOrderStatus.setText(orderStatus.resId)
                tvOrderSubjectContent.text = subject.content
                tvOrderPaymentStatus.setText(paymentStatus.resId)
                tvOrderCreateTime.text = createTime.formatDate()
            }

            root.setThrottleClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}