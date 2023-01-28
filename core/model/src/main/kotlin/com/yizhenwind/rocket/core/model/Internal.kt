package com.yizhenwind.rocket.core.model

import com.squareup.moshi.JsonClass

/**
 * 心法
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
@JsonClass(generateAdapter = true)
data class Internal(
    val id: Long = 0,
    val sectId: Long = 0,
    val name: String = "",
)
