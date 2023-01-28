package com.yizhenwind.rocket.core.model

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
data class AccountProfile(
    val id: Long = 0,
    val username: String = "",
    val characterCount: Int = 0,
    val orderCount: Int = 0,
    val createTime: Long = System.currentTimeMillis()
)