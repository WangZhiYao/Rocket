package com.yizhenwind.rocket.core.model

/**
 * 客户信息简略
 *
 * @author WangZhiYao
 * @since 2022/11/5
 */
data class ClientProfile(
    val id: Long,
    val name: String,
    val characterCount: Int,
    val orderCount: Int,
    val createTime: Long
)