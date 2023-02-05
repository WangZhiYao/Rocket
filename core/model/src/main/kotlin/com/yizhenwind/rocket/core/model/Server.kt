package com.yizhenwind.rocket.core.model

import com.squareup.moshi.JsonClass

/**
 * 服务器
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
@JsonClass(generateAdapter = true)
data class Server(
    val id: Long = 0,
    val zoneId: Long = 0,
    val name: String = ""
) {

    override fun toString(): String = name

}
