package com.yizhenwind.rocket.core.model

import androidx.recyclerview.widget.DiffUtil

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
data class Account(
    val id: Long = 0,
    val client: Client = Client(),
    val username: String = "",
    val password: String = "",
    val encrypted: Boolean = false,
    val iv: String = "",
    val createTime: Long = System.currentTimeMillis()
) {

    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<Account>() {

            override fun areItemsTheSame(
                oldItem: Account,
                newItem: Account
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Account,
                newItem: Account
            ): Boolean =
                oldItem == newItem
        }
    }
}