package com.yizhenwind.rocket.core.model

import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 * 客户
 *
 * @author WangZhiYao
 * @since 2022/3/25
 */
data class Client(
    val id: Long = 0,
    val name: String = "",
    val contactType: ContactType = ContactType.QQ,
    val contact: String = "",
    val remark: String = "",
    val createTime: Long = System.currentTimeMillis()
) {

    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<Client>() {

            override fun areItemsTheSame(
                oldItem: Client,
                newItem: Client
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Client,
                newItem: Client
            ): Boolean = oldItem == newItem
        }
    }

}