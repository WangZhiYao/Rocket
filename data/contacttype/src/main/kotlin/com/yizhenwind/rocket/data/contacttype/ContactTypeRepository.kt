package com.yizhenwind.rocket.data.contacttype

import com.yizhenwind.rocket.core.database.mapper.ContactTypeMapper
import com.yizhenwind.rocket.core.database.mapper.EntityListMapper
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.data.contacttype.source.ContactTypeLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
class ContactTypeRepository @Inject constructor(
    private val contactTypeLocalDataSource: ContactTypeLocalDataSource,
    private val contactTypeMapper: ContactTypeMapper
) {

    fun observeContactTypeList(): Flow<List<ContactType>> =
        contactTypeLocalDataSource.observeContactTypeList()
            .map {
                EntityListMapper(contactTypeMapper).fromEntity(it)
            }

    suspend fun getContactTypeByName(name: String): ContactType? =
        contactTypeLocalDataSource.getContactTypeByName(name)
            ?.run { contactTypeMapper.fromEntity(this) }

    fun createContactType(contactType: ContactType): Flow<Long> =
        contactTypeLocalDataSource.createContactType(contactTypeMapper.toEntity(contactType))

    fun updateContactType(contactType: ContactType): Flow<Int> =
        contactTypeLocalDataSource.updateContactType(contactTypeMapper.toEntity(contactType))

}