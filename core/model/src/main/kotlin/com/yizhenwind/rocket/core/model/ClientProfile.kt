package com.yizhenwind.rocket.core.model

/**
 * 客户信息简略
 *
 * @author WangZhiYao
 * @since 2022/11/5
 */
data class ClientProfile(
    val id: Long = 0,
    val name: String = "",
    val contactType: ContactType = ContactType(),
    val contact: String = "",
    val accountCount: Int = 0,
    val characterCount: Int = 0,
    val orderCount: Int = 0,
    val createTime: Long = 0
)