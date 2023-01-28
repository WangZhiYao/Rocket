package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.ContactTypeEntity
import com.yizhenwind.rocket.core.model.ContactType
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
class ContactTypeMapper @Inject constructor() : IEntityMapper<ContactTypeEntity, ContactType> {

    override fun fromEntity(entity: ContactTypeEntity): ContactType =
        entity.run { ContactType(id, name, default, enable) }

    override fun toEntity(model: ContactType): ContactTypeEntity =
        model.run { ContactTypeEntity(id, name, default, enable) }

}