package com.yizhenwind.rocket.core.database.converter

import androidx.room.TypeConverter
import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/6
 */
class ContactTypeConverter {

    @TypeConverter
    fun fromContactType(contactType: ContactType): Int =
        contactType.index

    @TypeConverter
    fun toContactType(index: Int): ContactType? =
        ContactType.getByIndex(index)

}