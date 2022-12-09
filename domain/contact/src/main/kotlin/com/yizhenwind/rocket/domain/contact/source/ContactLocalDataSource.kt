package com.yizhenwind.rocket.domain.contact.source

import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.database.dao.ContactDao
import com.yizhenwind.rocket.core.database.entity.ContactEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/3
 */
class ContactLocalDataSource @Inject constructor(
    private val contactDao: ContactDao
) {

    fun createContact(clientId: Long, contactType: ContactType, value: String): Flow<Long?> =
        flow {
            emit(
                contactDao.insert(
                    ContactEntity(
                        clientId = clientId,
                        type = contactType,
                        value = value
                    )
                )
            )
        }

    suspend fun getContactByTypeAndValue(contactType: ContactType, value: String): ContactEntity? =
        contactDao.getContactByTypeAndValue(contactType, value)

}