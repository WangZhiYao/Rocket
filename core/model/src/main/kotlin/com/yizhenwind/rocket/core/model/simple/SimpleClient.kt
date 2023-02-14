package com.yizhenwind.rocket.core.model.simple

import com.yizhenwind.rocket.core.model.ContactType

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class SimpleClient(
    val id: Long = 0,
    val name: String = "",
    val contactType: ContactType = ContactType(),
    val contact: String = ""
)