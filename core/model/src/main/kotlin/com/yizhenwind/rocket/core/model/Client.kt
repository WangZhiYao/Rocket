package com.yizhenwind.rocket.core.model

import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 * 客户
 *
 * @author WangZhiYao
 * @since 2022/3/25
 */
data class Client(
    val id: Long = 0,
    val name: String = "",
    val contactType: ContactType = ContactType.QQ,
    val contact: String = "",
    val remark: String = "",
    val createTime: Long = System.currentTimeMillis()
)