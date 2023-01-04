package com.yizhenwind.rocket.core.model

import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 * 联系方式
 *
 * @author WangZhiYao
 * @since 2022/12/2
 */
data class Contact(
    val id: Long = 0,
    val clientId: Long = 0,
    val type: ContactType = ContactType.QQ,
    val value: String = ""
)