package com.yizhenwind.rocket.core.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
data class Account(
    val id: Long = 0,
    val client: Client = Client(),
    val username: String = "",
    val password: String = "",
    val encrypted: Boolean = false,
    val iv: String = "",
    val enable: Boolean = true,
    val createTime: Long = System.currentTimeMillis()
) {

    override fun toString(): String = username

}