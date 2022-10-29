package com.yizhenwind.rocket.feature.customer.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.data.customer.model.Customer
import com.yizhenwind.rocket.feature.customer.databinding.ItemCustomerBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/29
 */
class CustomerAdapter : PagingDataAdapter<Customer, CustomerViewHolder>(CustomerItemCallback()) {

    internal class CustomerItemCallback : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder =
        CustomerViewHolder(parent.viewBinding(ItemCustomerBinding::inflate)).also { customerViewHolder ->
            customerViewHolder.onItemClickListener = { customer ->

            }
        }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        getItem(position)?.let { customer -> holder.bind(customer) }
    }

}