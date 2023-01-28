package com.yizhenwind.rocket.core.model

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
data class Subject(
    val id: Long = 0,
    val category: Category = Category(),
    val content: String = "",
    val enable: Boolean = true,
    val createTime: Long = System.currentTimeMillis()
)