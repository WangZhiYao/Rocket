package com.yizhenwind.rocket.core.model

import com.squareup.moshi.JsonClass

/**
 * 门派
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
@JsonClass(generateAdapter = true)
data class Sect(
    val id: Long = 0,
    val name: String = ""
)
