package com.yizhenwind.rocket.feature.paymentmethod.ui

import androidx.core.view.isVisible
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ui.BaseViewHolder
import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.feature.paymentmethod.databinding.ItemPaymentMethodBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
class PaymentMethodViewHolder(
    private val binding: ItemPaymentMethodBinding
) : BaseViewHolder<PaymentMethod>(binding.root) {

    var onDeleteClickListener: ((PaymentMethod) -> Unit)? = null

    override fun bind(item: PaymentMethod) {
        binding.apply {
            item.apply {
                tvPaymentMethodName.text = name
                ibPaymentMethodDelete.apply {
                    isVisible = !default
                    setThrottleClickListener {
                        onDeleteClickListener?.invoke(item)
                    }
                }
                root.setThrottleClickListener { onItemClickListener?.invoke(item) }
            }
        }
    }
}