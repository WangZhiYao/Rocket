package com.yizhenwind.rocket.feature.customer.ui

import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder
import com.yizhenwind.rocket.data.customer.model.Customer
import com.yizhenwind.rocket.feature.customer.databinding.ItemCustomerBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/30
 */
class CustomerViewHolder(private val binding: ItemCustomerBinding) :
    BaseViewHolder<Customer>(binding.root) {

    override fun bind(data: Customer) {
        binding.apply {

        }
    }

}