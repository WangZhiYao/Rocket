package com.yizhenwind.rocket.core.model

import com.squareup.moshi.JsonClass

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@JsonClass(generateAdapter = true)
data class OrderStatus(
    val id: Long = 0,
    val name: String = "",
    val default: Boolean = false,
    val enable: Boolean = true
)