package com.yizhenwind.rocket.core.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
data class Account(
    val id: Long = 0,
    val clientId: Long = 0,
    val username: String = "",
    val password: String = "",
    val enable: Boolean = true,
    val createTime: Long = System.currentTimeMillis()
)