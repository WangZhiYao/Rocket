package com.yizhenwind.rocket.core.database.dto

import androidx.room.ColumnInfo
import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class ClientTupleDto(
    val id: Long,
    val name: String,
    @ColumnInfo(name = "contact_type")
    val contactType: ContactType,
    val contact: String,
)