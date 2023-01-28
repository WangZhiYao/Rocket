package com.yizhenwind.rocket.core.model

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
    val description: String? = null,
    val default: Boolean = false,
    val enable: Boolean = true,
    val createTime: Long = System.currentTimeMillis()
)