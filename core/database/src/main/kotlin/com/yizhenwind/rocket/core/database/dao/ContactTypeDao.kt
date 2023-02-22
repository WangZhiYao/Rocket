package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.ContactTypeEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Dao
interface ContactTypeDao : IDao<ContactTypeEntity> {

    @Query("SELECT * FROM contact_type WHERE enable = 1")
    fun observeContactTypeList(): Flow<List<ContactTypeEntity>>

    @Query("SELECT * FROM contact_type WHERE name = :name")
    suspend fun getContactTypeByName(name: String): ContactTypeEntity?

}