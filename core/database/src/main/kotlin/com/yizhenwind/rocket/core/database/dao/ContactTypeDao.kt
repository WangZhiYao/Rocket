package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.ContactTypeEntity
import com.yizhenwind.rocket.core.model.ContactType
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Dao
interface ContactTypeDao : IDao<ContactTypeEntity> {

    @Query("SELECT * FROM contact_type WHERE enable = 1")
    fun observeContactType(): Flow<List<ContactTypeEntity>>

    @Query("SELECT * FROM contact_type WHERE name COLLATE NOCASE = :name")
    suspend fun getContactTypeByName(name: String): ContactTypeEntity?

}