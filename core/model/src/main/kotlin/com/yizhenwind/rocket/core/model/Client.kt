package com.yizhenwind.rocket.core.model

/**
 * 客户
 *
 * @author WangZhiYao
 * @since 2022/3/25
 */
data class Client(
    val id: Long = 0,
    val name: String = "",
    val contactType: ContactType = ContactType(),
    val contact: String = "",
    val remark: String? = null,
    val enable: Boolean = true,
    val createTime: Long = System.currentTimeMillis()
)