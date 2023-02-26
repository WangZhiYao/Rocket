package com.yizhenwind.rocket.core.model

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.JsonClass

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
@JsonClass(generateAdapter = true)
data class Category(
    val id: Long = 0,
    val title: String = "",
    val remark: String = "",
    val createTime: Long = System.currentTimeMillis()
) {
    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<Category>() {

            override fun areItemsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean = oldItem == newItem
        }
    }
}