package com.yizhenwind.rocket.feature.paymentmethod.ui

import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.framework.ui.BaseListAdapter
import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.feature.paymentmethod.databinding.ItemPaymentMethodBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
class PaymentMethodAdapter :
    BaseListAdapter<PaymentMethod, PaymentMethodViewHolder>(PaymentMethod.COMPARATOR) {

    var onDeleteClickListener: ((PaymentMethod) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder =
        PaymentMethodViewHolder(parent.viewBinding(ItemPaymentMethodBinding::inflate)).apply {
            onItemClickListener = { paymentMethod ->
                this@PaymentMethodAdapter.onItemClickListener?.invoke(paymentMethod)
            }
            onDeleteClickListener = { paymentMethod ->
                this@PaymentMethodAdapter.onDeleteClickListener?.invoke(paymentMethod)
            }
        }

}