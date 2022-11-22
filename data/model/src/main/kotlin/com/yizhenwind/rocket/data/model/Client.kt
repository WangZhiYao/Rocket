package com.yizhenwind.rocket.data.model

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
    var contactType: ContactType = ContactType.QQ,
    var contact: String = "",
    var remark: String? = null,
    val createTime: Long = System.currentTimeMillis()
)