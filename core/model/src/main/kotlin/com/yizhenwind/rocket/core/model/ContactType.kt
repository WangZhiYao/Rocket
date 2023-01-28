package com.yizhenwind.rocket.core.model

import com.squareup.moshi.JsonClass

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@JsonClass(generateAdapter = true)
data class ContactType(
    val id: Long = 0,
    val name: String = "",
    val default: Boolean = false,
    val enable: Boolean = true
) {

    companion object {

        const val QQ = 1L

        const val WECHAT = 2L

    }
}