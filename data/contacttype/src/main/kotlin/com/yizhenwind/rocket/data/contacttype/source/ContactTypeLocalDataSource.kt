package com.yizhenwind.rocket.data.contacttype.source

import com.yizhenwind.rocket.core.database.dao.ContactTypeDao
import com.yizhenwind.rocket.core.database.entity.ContactTypeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
class ContactTypeLocalDataSource @Inject constructor(
    private val contactTypeDao: ContactTypeDao
) {

    fun observeContactTypeList(): Flow<List<ContactTypeEntity>> =
        contactTypeDao.observeContactTypeList()

    suspend fun getContactTypeByName(name: String): ContactTypeEntity? =
        contactTypeDao.getContactTypeByName(name)

    fun createContactType(contactTypeEntity: ContactTypeEntity): Flow<Long> =
        flow {
            emit(contactTypeDao.insert(contactTypeEntity))
        }

    fun updateContactType(contactTypeEntity: ContactTypeEntity): Flow<Int> =
        flow {
            emit(contactTypeDao.update(contactTypeEntity))
        }

}