package com.yizhenwind.rocket.core.model

import androidx.recyclerview.widget.DiffUtil

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
data class Subject(
    val id: Long = 0,
    val category: Category = Category(),
    val content: String = "",
    val createTime: Long = System.currentTimeMillis()
) {

    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<Subject>() {

            override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean =
                oldItem == newItem

        }

    }
}