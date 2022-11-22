package com.yizhenwind.rocket.data.model

import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/5
 */
data class ClientProfile(
    val id: Long,
    val name: String,
    val contactType: ContactType,
    val contact: String,
    val accountCount: Int,
    val characterCount: Int,
    val orderCount: Int,
    val createTime: Long
)