package com.yizhenwind.rocket.core.model

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.JsonClass

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
@JsonClass(generateAdapter = true)
data class PaymentMethod(
    val id: Long = 0,
    val name: String = "",
    val default: Boolean = false,
    val enable: Boolean = true
) {

    override fun toString() = name

    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<PaymentMethod>() {

            override fun areItemsTheSame(
                oldItem: PaymentMethod,
                newItem: PaymentMethod
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PaymentMethod,
                newItem: PaymentMethod
            ): Boolean = oldItem == newItem

        }
    }
}
