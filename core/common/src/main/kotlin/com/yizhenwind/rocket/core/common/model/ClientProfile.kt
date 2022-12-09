package com.yizhenwind.rocket.core.common.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/5
 */
data class ClientProfile(
    val id: Long,
    val name: String,
    val accountCount: Int,
    val characterCount: Int,
    val orderCount: Int,
    val createTime: Long
)