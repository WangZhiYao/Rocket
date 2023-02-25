package com.yizhenwind.rocket.core.model

import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class ClientTuple(
    val id: Long = 0,
    val name: String = "",
    val contactType: ContactType = ContactType.QQ,
    val contact: String = ""
)