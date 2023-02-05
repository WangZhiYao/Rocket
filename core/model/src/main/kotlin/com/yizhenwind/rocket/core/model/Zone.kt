package com.yizhenwind.rocket.core.model

import com.squareup.moshi.JsonClass

/**
 * 大区
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
@JsonClass(generateAdapter = true)
data class Zone(
    val id: Long = 0,
    val name: String = ""
) {

    override fun toString(): String = name

}