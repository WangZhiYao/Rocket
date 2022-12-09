package com.yizhenwind.rocket.domain.contact.repository

import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.common.model.Contact
import com.yizhenwind.rocket.core.database.mapper.ContactMapper
import com.yizhenwind.rocket.core.infra.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.infra.ext.ifNullOrElse
import com.yizhenwind.rocket.domain.contact.source.ContactLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/3
 */
class ContactRepository @Inject constructor(
    private val contactLocalDataSource: ContactLocalDataSource,
    private val contactMapper: ContactMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    fun createContact(clientId: Long, contactType: ContactType, value: String): Flow<Contact> =
        contactLocalDataSource.createContact(clientId, contactType, value)
            .map { id ->
                id.ifNullOrElse({ Contact() }, { Contact(it, clientId, contactType, value) })
            }
            .flowOn(dispatcher)

    fun getContactByTypeAndValue(contactType: ContactType, value: String): Flow<Contact> =
        flow {
            emit(contactLocalDataSource.getContactByTypeAndValue(contactType, value))
        }
            .map { contactEntity ->
                contactEntity.ifNullOrElse({ Contact() }, { contactMapper.map(it) })
            }
            .flowOn(dispatcher)

}