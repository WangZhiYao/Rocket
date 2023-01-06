package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.entity.ContactEntity
import com.yizhenwind.rocket.core.model.Contact
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/3
 */
class ContactMapper @Inject constructor() : IMapper<ContactEntity, Contact> {

    override fun map(input: ContactEntity): Contact =
        input.run { Contact(id, clientId, type, value) }

}