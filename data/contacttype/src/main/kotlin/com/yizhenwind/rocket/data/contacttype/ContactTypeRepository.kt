package com.yizhenwind.rocket.data.contacttype

import com.yizhenwind.rocket.core.database.mapper.ContactTypeMapper
import com.yizhenwind.rocket.core.database.mapper.EntityListMapper
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.data.contacttype.source.ContactTypeLocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
class ContactTypeRepository @Inject constructor(
    private val contactTypeLocalSource: ContactTypeLocalSource,
    private val contactTypeMapper: ContactTypeMapper
) {

    fun observeContactType(): Flow<List<ContactType>> =
        contactTypeLocalSource.observeContactType()
            .map {
                EntityListMapper(contactTypeMapper).fromEntity(it)
            }

}