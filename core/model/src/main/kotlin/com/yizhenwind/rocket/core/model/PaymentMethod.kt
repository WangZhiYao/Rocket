package com.yizhenwind.rocket.core.model

import com.squareup.moshi.JsonClass

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
@JsonClass(generateAdapter = true)
data class PaymentMethod(
    val id: Long,
    val name: String,
    val default: Boolean = false,
    val enable: Boolean = true
)
