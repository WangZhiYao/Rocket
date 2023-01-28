package com.yizhenwind.rocket.data.contacttype.source

import com.yizhenwind.rocket.core.database.dao.ContactTypeDao
import com.yizhenwind.rocket.core.database.entity.ContactTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
class ContactTypeLocalSource @Inject constructor(
    private val contactTypeDao: ContactTypeDao
) {

    fun observeContactType(): Flow<List<ContactTypeEntity>> =
        contactTypeDao.observeContactType()

}