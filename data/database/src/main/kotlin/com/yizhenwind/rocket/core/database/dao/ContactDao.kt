package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.database.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/3
 */
@Dao
interface ContactDao : IDao<ContactEntity> {

    @Query("SELECT * FROM contact WHERE client_id = :clientId")
    fun observeContactList(clientId: Long): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contact WHERE type = :contactType AND value = :value LIMIT 1")
    suspend fun getContactByTypeAndValue(contactType: ContactType, value: String): ContactEntity?

}