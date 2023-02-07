package com.yizhenwind.rocket.core.model

import com.squareup.moshi.JsonClass

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
@JsonClass(generateAdapter = true)
data class Period(
    val id: Long,
    val value: String,
    val default: Boolean = false,
    val enable: Boolean = true
)
