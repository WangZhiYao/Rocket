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
    val contactList: List<Contact> = emptyList(),
    var remark: String? = null,
    val createTime: Long = System.currentTimeMillis()
)