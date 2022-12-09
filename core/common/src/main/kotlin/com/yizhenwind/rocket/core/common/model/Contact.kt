package com.yizhenwind.rocket.core.common.model

import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 *
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