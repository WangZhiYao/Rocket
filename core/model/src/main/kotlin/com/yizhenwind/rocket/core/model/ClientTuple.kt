package com.yizhenwind.rocket.core.model

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class ClientTuple(
    val id: Long = 0,
    val name: String = "",
    val contactType: ContactType = ContactType(),
    val contact: String = ""
)