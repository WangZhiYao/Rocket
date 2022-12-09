package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.model.Contact
import com.yizhenwind.rocket.core.database.entity.ContactEntity
import com.yizhenwind.rocket.core.infra.mapper.IMapper
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